package telecom.fa17.game;

/**
 * Représentation des quatre directions cardinales.
 */
public enum Direction {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3),
    NORTH_EAST(4),
    NORTH_WEST(5),
    SOUTH_EAST(6),
    SOUTH_WEST(7);

    /**
     * Indice.
     *
     */
    public final int index;
    
    Direction(int dir){ index = dir; }
}
