package racingcar.common;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBus {
    private final Map<Class<?>, List<EventHandler<?>>> handlers = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

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
                CompletableFuture.runAsync(() -> process(event, typedHandler), executor);
            });
        }
    }

    private void process(Object event, EventHandler<Object> typedHandler) {
        typedHandler.handle(event);
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public <T> void unsubscribe(Class<T> eventType, EventHandler<T> handler) {
        List<EventHandler<?>> list = handlers.get(eventType);
        if (list != null) {
            list.remove(handler);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
