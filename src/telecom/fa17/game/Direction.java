package telecom.fa17.game;

/**
 * Représentation des quatre directions cardinales.
 */
public enum Direction {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    /**
     * Indice.
     */
    public final int index;
    
    Direction(int dir){ index = dir; }
}
