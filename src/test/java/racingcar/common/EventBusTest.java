package racingcar.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBusTest {
    private final EventBus eventBus = new EventBus();

    private record DummyStringEvent(String message) {
    }

    private DummyStringEvent helloDummyStringEvent() {
        String messageForDelivery = "hello";
        return new DummyStringEvent(messageForDelivery);
    }

    @AfterEach
    void tearDown() {
        eventBus.shutdown();
    }

    @Test
    @DisplayName("이벤트가 발행되면 구독자는 이벤트를 정상적으로 수신해야 한다")
    void publishShouldDeliverEventToSubscriber() throws Exception {
        // given
        CompletableFuture<Object> future = new CompletableFuture<>();
        eventBus.subscribe(DummyStringEvent.class, future::complete);
        DummyStringEvent publishedEvent = helloDummyStringEvent();

        //when
        eventBus.publish(publishedEvent);

        //then
        Object actual = future.get(2, TimeUnit.SECONDS);
        assertEquals(publishedEvent, actual);
    }

    @Test
    @DisplayName("이벤트가 발행되면 구독자들은 이벤트를 정상적으로 수신해야 한다")
    void publishShouldDeliverEventToSubscribers() throws Exception {
        // given
        DummyStringEvent publishedEvent = helloDummyStringEvent();

        CompletableFuture<Object> future = new CompletableFuture<>();
        CompletableFuture<Object> anotherFuture = new CompletableFuture<>();

        eventBus.subscribe(DummyStringEvent.class, future::complete);
        eventBus.subscribe(DummyStringEvent.class, anotherFuture::complete);

        //when
        eventBus.publish(publishedEvent);

        //then
        assertEquals(publishedEvent, future.get());
        assertEquals(publishedEvent, anotherFuture.get());

        assertEquals(future.get(), anotherFuture.get());
    }

    @DisplayName("구독자는 발행된 이벤트에 담긴 값을 정상적으로 수신해야 한다")
    @Test
    void publishedValueShouldDeliverEventToSubscriber() throws Exception {
        // given
        String messageForDelivery = "hello";
        DummyStringEvent dummyEvent = new DummyStringEvent(messageForDelivery);
        CompletableFuture<DummyStringEvent> future = new CompletableFuture<>();
        eventBus.subscribe(DummyStringEvent.class, future::complete);

        //when
        eventBus.publish(dummyEvent);

        //then
        Object actual = future.get(2, TimeUnit.SECONDS).message();
        assertEquals(messageForDelivery, actual);
    }

    @DisplayName("unsubscribe() 호출 시, 이후 발행되는 이벤트는 전달되지 않아야 한다.")
    @Test
    void unsubscribeShouldPreventFurtherDelivery() {
        // given
        DummyStringEvent dummyEvent = helloDummyStringEvent();
        CompletableFuture<Object> future = new CompletableFuture<>();

        EventHandler<DummyStringEvent> handler = future::complete;
        eventBus.subscribe(DummyStringEvent.class, handler);
        eventBus.unsubscribe(DummyStringEvent.class, handler);

        //when
        eventBus.publish(dummyEvent);

        //then
        assertThrows(TimeoutException.class, () -> future.get(1, TimeUnit.SECONDS));
    }

    @DisplayName("이벤트 핸들러는 메인 스레드가 아닌 별도 스레드에서 비동기로 실행되어야 한다")
    @Test
    void handlerShouldRunOnDifferentThread() throws Exception {
        // given
        String mainThread = Thread.currentThread().getName();
        CompletableFuture<String> future = new CompletableFuture<>();

        eventBus.subscribe(String.class, future::complete);

        //when
        eventBus.publish("check thread");

        //then
        String handlerThread = future.get(2, TimeUnit.SECONDS);
        assertNotEquals(mainThread, handlerThread);
    }
}
