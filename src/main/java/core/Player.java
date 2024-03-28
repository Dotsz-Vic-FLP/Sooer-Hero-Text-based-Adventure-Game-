package core;
import command.ImpossibleCommandException;


import java.util.ArrayList;


/**
 * Represents the player in the game.
 */
public class Player {
    private String name;
    private String description;
    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();

    private boolean hasKey = false;


    /**
     * Creates a new player with the given name and description.
     *
     * @param name        The name of the player.
     * @param description The description of the player.
     * @param currentRoom The room the player is currently in.
     */
    public Player(String name, String description, Room currentRoom) {
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
    }
    /**
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }
    /**
     * @return The description of the player.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return The room the player is currently in.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Move the player in the given direction.
     *
     * @param direction The direction in which to move the player.
     * @throws ImpossibleCommandException If the player can't go in the given direction.
     */
    public void move(Direction direction) throws ImpossibleCommandException {
        if (!currentRoom.hasExit(direction)) { // check if the current room has an exit in the given direction
            throw new ImpossibleCommandException("You can't go in that direction.");
        }

        currentRoom = currentRoom.getDestination(direction);
    }
    /**
     * @return A description of the room the player is currently in.
     */
    public String look() {
        return this.currentRoom.getDescription();
    }
    /**
     * Add an item to the player's inventory.
     *
     * @param item The item to add to the player's inventory.
     */
    public void takeItem(Item item) {
        this.inventory.add(item);
    }
    /**
     * Drop an item from the player's inventory.
     *
     * @param item The item to drop from the player's inventory.
     */
    public void dropItem(Item item) {
        this.inventory.remove(item);
    }
    /**
     * @return A list of items in the player's inventory.
     */
    public ArrayList<Item> getInventory() {
        return new ArrayList<>(this.inventory);
    }

    public boolean hasKey() {
        return hasKey;
    }
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
}
