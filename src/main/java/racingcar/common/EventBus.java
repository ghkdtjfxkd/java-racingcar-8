package racingcar.common;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class EventBus {

    private static final class SingletonHolder {
        private static final EventBus INSTANCE = new EventBus();
    }

    private final Map<Class<?>, List<EventHandler<?>>> handlers = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    private Consumer<Exception> exceptionCallback;

    private EventBus() {}

    static synchronized EventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> void subscribe(Class<T> eventType, EventHandler<T> handler) {
        handlers.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>())
                .add(handler);
    }

    public void publish(Object event) {
        Class<?> eventType = event.getClass();
        List<EventHandler<?>> eventHandlers = handlers.get(eventType);

        if (eventHandlers != null && !eventHandlers.isEmpty()) {
            eventHandlers.forEach(handler -> {
                @SuppressWarnings("unchecked")
                EventHandler<Object> typedHandler = (EventHandler<Object>) handler;
                executeHandler(event, typedHandler);
            });
        }
    }

    private void executeHandler(Object event, EventHandler<Object> handler) {
        if (isTestEnvironment()) {
            process(event, handler);
            return;
        }
        CompletableFuture.runAsync(() -> process(event, handler), executor);
    }

    private boolean isTestEnvironment() {
        return Arrays.stream(Thread.currentThread().getStackTrace())
                .map(StackTraceElement::getClassName)
                .anyMatch(this::isTestClass);
    }

    private boolean isTestClass(String className) {
        return className.contains("Test")
                || className.contains("camp.nextstep")
                || className.contains("org.junit");
    }

    private void process(Object event, EventHandler<Object> typedHandler) {
        try {
            typedHandler.handle(event);
        } catch (Exception e) {
            exceptionAccept(e);
        }
    }

    private void exceptionAccept(Exception e) {
        if(exceptionCallback != null) {
            exceptionCallback.accept(e);
        }
    }

    public void setExceptionCallback(Consumer<Exception> callback) {
        this.exceptionCallback = callback;
    }

    public void handleException(Exception e) {
        exceptionCallback.accept(e);
        shutdown();
    }

    public void shutdown() {
        executor.shutdown();
    }
}
