package core;

import org.junit.jupiter.api.Test;
import command.ImpossibleCommandException;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    /**
     * Test the player move method.
     */
    @Test
    void testPlayerMove() throws ImpossibleCommandException{
        Room testStartRoom = new Room("You are in a room"); // create a test room
        Room testOtherRoom = new Room("You are in another room"); // create an adjacent test room
        testStartRoom.connect(Direction.WEST, testOtherRoom); // set connection between rooms
        Player player = new Player("Hero", "You are a test hero. You are in a room", testStartRoom); // set player in the first room

        player.move(Direction.WEST); // move the player to the other room

        assertEquals("You are in another room", testOtherRoom.getDescription(), player.getCurrentRoom().getDescription());
    }

    /**
     * Test the player inventory methods.
     */
    @Test
    void testPlayerInventory() {
        Player testplayer = new Player("Hero", "You are a test hero. You are in a room", new Room("You are in a room"));
        Item testItem = new Item("potato", "a potato");

        testplayer.takeItem(testItem);
        assertTrue(testplayer.getInventory().contains(testItem));

        testplayer.dropItem(testItem);
        assertFalse(testplayer.getInventory().contains(testItem));
    }
}