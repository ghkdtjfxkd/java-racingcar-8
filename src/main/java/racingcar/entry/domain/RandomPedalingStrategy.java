package racingcar.entry.domain;

import camp.nextstep.edu.missionutils.Randoms;

class RandomPedalingStrategy implements PedalingStrategy {

    private static final int MIN_PEDALING_RANGE = 0;
    private static final int MAX_PEDALING_RANGE = 9;

    private static final int REPULSIVE_OF_PEDAL = 4;

    @Override
    public boolean isEffective() {
        int value = pushingPedal();
        System.out.println("Randoms called: " + value);  // 추가
        return value >= REPULSIVE_OF_PEDAL;
    }

    private int pushingPedal() {
        return Randoms.pickNumberInRange(MIN_PEDALING_RANGE, MAX_PEDALING_RANGE);
    }
}
