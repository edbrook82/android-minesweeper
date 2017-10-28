package uk.co.dekoorb.c3469162.minesweeper.tools;

import java.util.ArrayList;

import uk.co.dekoorb.c3469162.minesweeper.model.GeoLocation;
import uk.co.dekoorb.c3469162.minesweeper.model.Mine;

/**
 * Created by c3469162 on 27/10/2017.
 */

public final class MineGenerator {
    public static final double MIN_LAT = 53.825425;
    public static final double MAX_LAT = 53.826914;

    public static final double MIN_LON = -1.593775;
    public static final double MAX_LON = -1.592060;

    private MineGenerator() {
    }

    private static boolean genCleared(double prob) {
        if (prob == 0) {
            return false;
        } else if (prob == 1) {
            return true;
        } else if (prob > 0 && prob < 1) {
            return Math.random() >= prob;
        } else {
            return false;
        }
    }

    public static GeoLocation genLocation() {
        double lat = MIN_LAT + Math.random() * (MAX_LAT - MIN_LAT);
        double lon = MIN_LON + Math.random() * (MAX_LON - MIN_LON);
        return new GeoLocation(lat, lon);
    }

    public static ArrayList<Mine> genMines(int count, double clearedProb) {
        ArrayList<Mine> mines = new ArrayList<>();
        Mine mine;
        for (long i=0; i<count; ++i) {
            mine = new Mine(i, genLocation(), genCleared(clearedProb));
            mines.add(mine);
        }
        return mines;
    }
}
