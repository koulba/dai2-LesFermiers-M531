package command;

import main.WorldMap;
import core.Location;
import utils.Array2Dprinter;

public class CommandMap implements Command {
    private final WorldMap map;

    public CommandMap(WorldMap map) {
        this.map = map;
    }

    @Override
    public void execute(String[] args) {
        System.out.println(
                Array2Dprinter.print2DArray(
                        map.getLocations(),
                        map.getPlayerRow(),
                        map.getPlayerCol()
                )
        );
    }
}