import core.*;

/**
 * This class is used to initialize the map and items of a game.
 * It is not part of the game itself, but instead creates all the entities needed for a game.
 * Feel free to modify this class to change any aspects of the game,
 * or create other classes that could be used to initialize different games.
 */
public class GameInitializer {

    /**
     * @return a new {@link core.Player} instance that represents the player in their starting room.
     */
    public static Player init() {

        var tunnel = new Room(
                "You are in a long, dark tunnel. There is a door to the north. To the south, the tunnel leads into darkness."
        );

        var startRoom = new Room(
                "You find yourself in a dark, smelly sewer. It is very cold. There is a door to the south. To the north, the sewer leads into dripping darkness. East and west of you are two more sewer tunnels that seem to open into larger spaces."
        );

        var westRoom = new Room(
                "You are in a chamber.  The only exit is back east to where you started."
        );

        var eastRoom = new Room(
                "You are in a chamber.  The only exit is back west to where you started."
        );


        var topRoom = new Room(
                "You are in a chamber.  The only exit is back south into the dark tunnel."
        );

        var endRoom = new Room(
                "You step out into the light of day. The sun is warm and invigorating. You have performed feats both great and terrible, but as the wind rustles the grasses before you a new life seems possible...",
                true
        );

        endRoom.setLock(true);

        startRoom.connect(Direction.NORTH, tunnel);
        startRoom.connect(Direction.SOUTH, endRoom);
        startRoom.connect(Direction.WEST, westRoom);
        startRoom.connect(Direction.EAST, eastRoom);
        tunnel.connect(Direction.NORTH, topRoom);



        var key = new Item("key", "A small, rusty key.");
        var note = new Item("note", "A soggy note with 'The key is in the north chamber' written in shaky scrawl.");
        var shield = new Item("shield", "A shield of persistent study");
        topRoom.addItem(key);
        westRoom.addItem(note);
        eastRoom.addItem(shield);

        var player = new Player("Sooerman", "Like Superman, but shittier.", startRoom);
        return player;
    }

}
