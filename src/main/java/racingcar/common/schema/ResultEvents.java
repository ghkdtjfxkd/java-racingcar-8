package racingcar.common.schema;

import java.util.List;

import racingcar.Application;

import racingcar.result.ResultEventHandler;

public class ResultEvents {

    /**
     * 승자 결정됨 이벤트
     * <p>
     * <b>발행:</b> {@link ResultEventHandler}
     * <br>
     * <b>구독:</b> {@link Application}
     *
     * @param names 승자 이름들
     */
    public record WinnersDetermined(List<String> names) {
    }
}
