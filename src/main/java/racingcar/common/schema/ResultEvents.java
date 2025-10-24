package racingcar.common.schema;

import java.util.List;

public class ResultEvents {
    public record WinnersDetermined(List<String> names) {}
}
