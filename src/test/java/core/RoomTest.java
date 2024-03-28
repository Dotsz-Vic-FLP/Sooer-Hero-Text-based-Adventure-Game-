package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class RoomTest {

    Room center;
    Room north;
    Room south;
    Room east;
    Room west;

//    Item item1;
//    Item item2;
//    Item item3;

    @BeforeEach
    void setUp() {
        center = new Room("center");
        north = new Room("north");
        south = new Room("south");
        east = new Room("east");
        west = new Room("west");

//        item1 = new Item("item1", "item1 description", 0, 0);
//        item2 = new Item("item2", "item2 description", 0, 0);
//        item3 = new Item("item3", "item3 description", 0, 0);
    }

    // The testXConnection methods effectively test both the 'connect' and 'getDestination' methods
    @Test
    void testSouthConnection() {
        center.connect(Direction.SOUTH, south);

        assertEquals(south, center.getDestination(Direction.SOUTH), "A connection to the south should give a room a south destination");
        assertEquals(center, south.getDestination(Direction.NORTH), "A connection to the south should give the room to the south a north destination");

        assertNull(center.getDestination(Direction.WEST), "A connection to the south should not give a room a west destination");
        assertNull(center.getDestination(Direction.EAST), "A connection to the south should not give a room a east destination");
        assertNull(center.getDestination(Direction.NORTH), "A connection to the south should not give a room a north destination");
    }

    @Test
    void testNorthConnection() {
        center.connect(Direction.NORTH, north);

        assertEquals(north, center.getDestination(Direction.NORTH), "A connection to the north should give a room a north destination");
        assertEquals(center, north.getDestination(Direction.SOUTH), "A connection to the north should give the room to the north a south destination");

        assertNull(center.getDestination(Direction.WEST), "A connection to the north should not give a room a west destination");
        assertNull(center.getDestination(Direction.EAST), "A connection to the north should not give a room a east destination");
        assertNull(center.getDestination(Direction.SOUTH), "A connection to the north should not give a room a south destination");
    }

    @Test
    void testEastConnection() {
        center.connect(Direction.EAST, east);

        assertEquals(east, center.getDestination(Direction.EAST), "A connection to the east should give a room a east destination");
        assertEquals(center, east.getDestination(Direction.WEST), "A connection to the east should give the room to the east a west destination");

        assertNull(center.getDestination(Direction.WEST), "A connection to the east should not give a room a west destination");
        assertNull(center.getDestination(Direction.SOUTH), "A connection to the east should not give a room a south destination");
        assertNull(center.getDestination(Direction.NORTH), "A connection to the east should not give a room a north destination");
    }

    @Test
    void testWestConnection() {
        center.connect(Direction.WEST, west);

        try {
            assertEquals(west, center.getDestination(Direction.WEST), "A connection to the west should give a room a west destination");
            assertEquals(center, west.getDestination(Direction.EAST), "A connection to the west should give the room to the west a east destination");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }

        assertNull(center.getDestination(Direction.EAST), "A connection to the east should not give a room a east destination");
        assertNull(center.getDestination(Direction.SOUTH), "A connection to the east should not give a room a south destination");
        assertNull(center.getDestination(Direction.NORTH), "A connection to the east should not give a room a north destination");
    }

    // TODO: Test the 'isEndRoom' method
    @Test
    void testEndRoom() {
        var room = new Room("room", true);
        assertTrue(room.isEndRoom(), "A room should be able to be set as an end room");
        assertFalse(north.isEndRoom(), "A non-end room should not be an end room");
    }


    @Test
    void testAddandRemoveItem(){
        Room room = new Room("Test Room");
        Item item1 = new Item("Potato","Yup, just a potat!");
        Item item2 = new Item ("Carrot","Yup, just a carrot!");

        room.addItem(item1);
        room.addItem(item2);

        assertTrue(room.getItems().contains(item1));
        assertTrue(room.getItems().contains(item2));

        room.removeItem(item2);
        assertFalse(room.getItems().contains(item2));

    }
}