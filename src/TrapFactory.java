import whale.util.Coordinate;

public class TrapFactory {

    static ITrap makeTrap(Map map, TrapType targetType) {
        Coordinate targetCoordinate = map.findRandomEmptyCell(new NoArea());
        ITrap trap = null;
        if (targetType == TrapType.Dart) {
            trap = new DartTrap(targetCoordinate);
        } else if (targetType == TrapType.Spike) {
            trap = new SpikeTrap(targetCoordinate);
        } else if (targetType == TrapType.SpikedBoard) {
            trap = new SpikedBoardTrap(targetCoordinate);
        }

        // TODO Is placing the trigger on the same cell as the trap disabled?
        Coordinate possibleTriggerLocation = map.findRandomEmptyCell(trap.getTriggerArea());
        trap.connectTrapTrigger(possibleTriggerLocation);

        return trap;
    }
}
