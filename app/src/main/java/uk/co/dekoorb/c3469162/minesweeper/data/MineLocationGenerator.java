package uk.co.dekoorb.c3469162.minesweeper.data;

import java.util.ArrayList;

import uk.co.dekoorb.c3469162.minesweeper.model.MineLocation;

/**
 * Created by c3469162 on 27/10/2017.
 */

public final class MineLocationGenerator {
    public static final double MIN_LAT = 53.826914;
    public static final double MIN_LON = -1.593775;

    private MineLocationGenerator() {
    }

    public static ArrayList<MineLocation> genMineLocations(int count) {
        ArrayList<MineLocation> mines = new ArrayList<>();
        return mines;
    }
}
