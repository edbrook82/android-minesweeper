package uk.co.dekoorb.c3469162.minesweeper;

import org.junit.Test;

import java.util.List;

import uk.co.dekoorb.c3469162.minesweeper.tools.MineGenerator;
import uk.co.dekoorb.c3469162.minesweeper.model.GeoLocation;
import uk.co.dekoorb.c3469162.minesweeper.model.Mine;

import static org.junit.Assert.*;

/**
 * Created by edbrook on 28/10/2017.
 */

public class MineGeneratorTest {
    private final int NUM_MINES = 100;

    @Test
    public void lat_within_limits() {
        for (int i=0; i<NUM_MINES; ++i) {
            GeoLocation loc = MineGenerator.genLocation();
            double lat = loc.getLatitude();
            assertTrue(lat >= MineGenerator.MIN_LAT && lat <= MineGenerator.MAX_LAT);
        }
    }

    @Test
    public void lon_within_limits() {
        for (int i=0; i<NUM_MINES; ++i) {
            GeoLocation loc = MineGenerator.genLocation();
            double lon = loc.getLongitude();
            assertTrue(lon >= MineGenerator.MIN_LON && lon <= MineGenerator.MAX_LON);
        }
    }

    @Test
    public void mine_count() {
        final double PROB_CLEARED = 0;
        List<Mine> mines = MineGenerator.genMines(NUM_MINES, PROB_CLEARED);
        assertEquals(NUM_MINES, mines.size());
    }

    @Test
    public void mine_id_check() {
        final double PROB_CLEARED = 0;
        List<Mine> mines = MineGenerator.genMines(NUM_MINES, PROB_CLEARED);
        long id = 0;
        for (Mine mine : mines) {
            assertEquals(id, mine.getId());
            id++;
        }
    }

    @Test
    public void mine_non_cleared() {
        final double PROB_CLEARED = 0;
        List<Mine> mines = MineGenerator.genMines(NUM_MINES, PROB_CLEARED);
        for (Mine mine : mines) {
            assertEquals(false, mine.isCleared());
        }
    }

    @Test
    public void mine_some_cleared() {
        final double PROB_CLEARED = 0.5;
        List<Mine> mines = MineGenerator.genMines(NUM_MINES, PROB_CLEARED);
        int cleared = 0;
        for (Mine mine : mines) {
            cleared += mine.isCleared() ? 1 : 0;
        }
        assertTrue(cleared > (NUM_MINES / 2 * 0.9)
                && cleared < (NUM_MINES / 2 * 1.1));
    }

    @Test
    public void mine_all_cleared() {
        final double PROB_CLEARED = 1.0;
        List<Mine> mines = MineGenerator.genMines(NUM_MINES, PROB_CLEARED);
        for (Mine mine : mines) {
            assertEquals(true, mine.isCleared());
        }
    }
}
