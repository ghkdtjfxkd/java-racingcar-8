package racingcar.race;

public interface RaceService {
    void startRace(String lapCountInput);

    void executeNextLap();

    boolean isFinished();
}
