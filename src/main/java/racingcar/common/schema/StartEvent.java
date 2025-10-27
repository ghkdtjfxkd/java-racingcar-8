package racingcar.common.schema;

import racingcar.common.RacingCarGames;
import racingcar.io.input.InputEventAdapter;

/**
 * 시작 이벤트
 * <p>
 * <b>발행:</b> {@link RacingCarGames}
 * <br>
 * <b>구독:</b> {@link InputEventAdapter}
 */
public record StartEvent() {
}
