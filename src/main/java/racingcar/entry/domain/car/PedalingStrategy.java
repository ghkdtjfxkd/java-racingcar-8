package racingcar.entry.domain.car;

@FunctionalInterface
interface PedalingStrategy {
    boolean isEffective();
}
