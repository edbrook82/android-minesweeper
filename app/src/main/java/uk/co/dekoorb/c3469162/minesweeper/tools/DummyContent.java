package uk.co.dekoorb.c3469162.minesweeper.tools;

import android.util.LongSparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.dekoorb.c3469162.minesweeper.model.Mine;

public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Mine> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final LongSparseArray<Mine> ITEM_MAP = new LongSparseArray<>();

    private static final int COUNT = 15;

    static {
        for (Mine mine : MineGenerator.genMines(COUNT, 0.2)) {
            addItem(mine);
        }
    }

    private static void addItem(Mine mine) {
        ITEMS.add(mine);
        ITEM_MAP.put(mine.getId(), mine);
    }
}
