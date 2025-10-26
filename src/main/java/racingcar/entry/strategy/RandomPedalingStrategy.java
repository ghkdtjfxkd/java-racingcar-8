package racingcar.entry.strategy;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomPedalingStrategy implements PedalingStrategy {

    private static final int MIN_PEDALING_RANGE = 0;
    private static final int MAX_PEDALING_RANGE = 9;

    @Override
    public int pushing() {
        return Randoms.pickNumberInRange(MIN_PEDALING_RANGE, MAX_PEDALING_RANGE);
    }
}
