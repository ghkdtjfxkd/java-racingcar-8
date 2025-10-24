package racingcar.entry.domain;

@FunctionalInterface
interface PedalingStrategy {
    boolean isEffective();
}
