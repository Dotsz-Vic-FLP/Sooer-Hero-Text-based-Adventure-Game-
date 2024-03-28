package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A room in the game. A room may have exits in any of the four cardinal directions (@see {@link Direction}).
 * A room may also contain items for the player to pick up
 * If a room is an "end room" (@see {@link #isEndRoom}), the player wins the game when they enter the room.
 */
public class Room {
    /**
     * A description of the room.
     */
    private String description;
    /**
     * The available exits from the room, in one of the four cardinal direction. (@see {@link Direction}).
     */
    private Map<Direction, Exit> exits = new HashMap<>();
    /**
     * A boolean that is true if the room is an "end room", false otherwise.
     */
    private boolean isEndRoom;
    /**
     * A list of items in the room.
     */
    private ArrayList<Item> Items = new ArrayList<>();
    private boolean isLocked;
    /**
     * Creates a new room with the given description.
     * @param description A description of the room.
     */
    public Room(String description) {
        this.description = description;
    }
    /**
     * @return A description of the room.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Connects this room to another room in the given direction.  This will ultimately add one exit to both rooms.
     * The exit in this room will lead to the other room when the user moves in the given direction.
     * The exit in the other room will lead to this room in the opposite direction as the one given.
     * @param direction The direction that the other room is relative to this room
     * @param other The other room
     */
    public void connect(Direction direction, Room other) {
        var otherDirection = direction.getOpposite();
        exits.put(direction, new Exit(other));
        other.exits.put(otherDirection, new Exit(this));
    }

    /**
     * @param direction The direction in which to check for an exit.
     * @return True if there is an exit in the given direction, false otherwise.
     */
    public boolean hasExit(Direction direction) {
        return exits.containsKey(direction);
    }

    /**
     * @param direction The direction in which to check for an exit.
     * @return The destination room for the exit in the given direction, or null if no exit exists in that direction.
     */
    public Room getDestination(Direction direction) {
        var exit = exits.get(direction);
        if ( exit == null ) {
            return null;
        }
        return exit.destination();
    }

    /**
     * An exit represents a connection from one room to another.
     * It has a destination room.
     */
    public record Exit(Room destination) { }

    /**
     * @return True if this room is an "end room", false otherwise.
     */
    public boolean isEndRoom() {
        return this.isEndRoom;
    }

    /**
     * Creates a new room with the given description.
     * @param description A description of the room.
     * @param isEndRoom True if this room is an "end room", false otherwise.
     */
    public Room(String description, boolean isEndRoom){
        this.description = description;
        this.isEndRoom = isEndRoom;
    }

    /**
     * Add an item to the room.
     * @param item The item to add to the room.
     */
    public void addItem(Item item){
        this.Items.add(item);
    }
    /**
     * Remove an item from the room.
     * @param item The item to remove from the room.
     */
    public void removeItem(Item item) {
        this.Items.remove(item);
    }
    /**
     * @return A list of items in the room.
     */
    public ArrayList<Item> getItems(){
        return new ArrayList<>(this.Items);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLock(boolean isLocked) {
        this.isLocked = isLocked;
    }
}
