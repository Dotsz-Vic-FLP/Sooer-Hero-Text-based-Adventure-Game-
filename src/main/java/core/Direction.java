package core;

/**
 * An enum representing the four cardinal directions.
 */
public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    /**
     * @return the opposite direction of the direction on which this method is called.
     */
    public Direction getOpposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }

    /**
     * @param direction A string representing a direction.
     *                  The string can be either the full name of the direction, or the first letter of the direction.
     *                  For example, "north" or "n" are both valid.
     * @throws IllegalArgumentException if the string is not one of the four cardinal directions.
     * @return The direction corresponding to the given string, or null if the string does not represent a direction.
     */
    public static Direction fromString(String direction) {
        return switch (direction.toLowerCase()) {
            case "north", "n" -> NORTH;
            case "south", "s" -> SOUTH;
            case "east", "e" -> EAST;
            case "west", "w" -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }
}
