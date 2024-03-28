package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void getOpposite() {
        assertEquals(Direction.SOUTH, Direction.NORTH.getOpposite());
        assertEquals(Direction.NORTH, Direction.SOUTH.getOpposite());
        assertEquals(Direction.WEST, Direction.EAST.getOpposite());
        assertEquals(Direction.EAST, Direction.WEST.getOpposite());
    }

    @Test
    void fromString() {
        assertEquals(Direction.NORTH, Direction.fromString("North"));
        assertEquals(Direction.NORTH, Direction.fromString("N"));
        assertEquals(Direction.NORTH, Direction.fromString("n"));
        assertEquals(Direction.SOUTH, Direction.fromString("South"));
        assertEquals(Direction.SOUTH, Direction.fromString("S"));
        assertEquals(Direction.SOUTH, Direction.fromString("s"));
        assertEquals(Direction.EAST, Direction.fromString("East"));
        assertEquals(Direction.EAST, Direction.fromString("E"));
        assertEquals(Direction.EAST, Direction.fromString("e"));
        assertEquals(Direction.WEST, Direction.fromString("West"));
        assertEquals(Direction.WEST, Direction.fromString("W"));
        assertEquals(Direction.WEST, Direction.fromString("w"));
        assertThrows(IllegalArgumentException.class, () -> Direction.fromString("invalid"));
    }
}