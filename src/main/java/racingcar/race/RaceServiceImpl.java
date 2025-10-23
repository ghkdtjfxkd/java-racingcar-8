package racingcar.race;

import racingcar.race.domain.Race;

public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    public RaceServiceImpl() {
        this.raceRepository = new RaceRepository();
    }

    @Override
    public void startRace(String lapCountInput) {
        raceRepository.create(Race.from(lapCountInput));
    }

    @Override
    public void executeNextLap() {
        Race race = raceInProgress();
        race.advanceLap();
        raceRepository.update(race);
    }

    @Override
    public boolean isFinished() {
        return raceInProgress().hasRemainingLaps();
    }

    private Race raceInProgress() {
        return raceRepository.getRace();
    }
}
