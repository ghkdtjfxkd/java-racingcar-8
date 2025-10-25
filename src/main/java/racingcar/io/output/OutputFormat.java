package racingcar.io.output;

import java.util.List;
import java.util.StringJoiner;

enum OutputFormat {
    RACING_RECORDS("%s : %s"),
    WINNERS("최종 우승자 : %s");

    private static final String MILEAGE_SIGN = "-";
    private static final String WINNER_DELIMITER = ", ";
    private final String form;

    OutputFormat(String form) {
        this.form = form;
    }

    static String from(String name, int mileagePoint) {
        return String.format(RACING_RECORDS.form(), name, MILEAGE_SIGN.repeat(mileagePoint));
    }

    static String from(List<String> names) {
        StringJoiner joiner = new StringJoiner(WINNER_DELIMITER);
        names.forEach(joiner::add);

        return String.format(WINNERS.form(), joiner);
    }

    String form() {
        return form;
    }
}
