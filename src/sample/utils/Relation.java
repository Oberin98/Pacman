package sample.utils;

import sample.enums.Cell;

import java.util.HashMap;

public class Relation {
    /**
     * Relation between value from level.txt and state of the cell
     */
    final private static HashMap RELATION = new HashMap<String, Cell>() {{
        put("W", Cell.WALL);
        put("E", Cell.EMPTY);
        put("D", Cell.DOT);
        put("P", Cell.PACMAN);
        put("1", Cell.GHOST_1);
        put("2", Cell.GHOST_2);
    }};

    public static Cell get(String key) {
        return (Cell) RELATION.get(key);
    }

}
