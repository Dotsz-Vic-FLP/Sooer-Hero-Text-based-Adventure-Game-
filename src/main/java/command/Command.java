package command;

import core.Direction;
import core.Item;
import core.Player;
import core.Room;
import ui.UI;

import java.util.Map;

public class Command {

    /**
     * Print the help message.
     */
    public static void help() {
        var commandInfo = Map.of(
                "go <direction>", "Move in the given direction, one of N, S, E or W.",
                "help", "Show this help message.",
                "look", "Look around you.",
                "quit", "Quit the game.",
                "status", "Show your current status.",
                "take <item>", "Take the named item."
        );
        UI.help("Sooer Mage Commands", "", commandInfo);
    }

    /**
     * Move the player in the given direction.
     * @param player The player to move.
     * @param directionName The name of the direction to move in. Must be one of N, S, E or W. Case insensitive.
     * @throws ImpossibleCommandException If the player can't go in the direction indicated by the direction name.
     */
    public static void go(Player player, String directionName) throws ImpossibleCommandException{
        var direction = Direction.fromString(directionName);
        if (direction != null) {
            player.move(direction);
            if (player.getCurrentRoom().isEndRoom() && player.hasKey()){
                player.getCurrentRoom().setLock(false);
            }
            else if (player.getCurrentRoom().isEndRoom() && !player.hasKey()){
                UI.error("The door is locked. You need a key to unlock it.");
                player.move(direction.getOpposite());
            }
            else {
                UI.print(player.look());
            }

        }
        else {
            throw new ImpossibleCommandException("Invalid direction: " + directionName);
        }

    }

    /**
     * Print the player's status.
     * @param player The player to print the status of.
     */
    public static void status(Player player) {
        var inventory = player.getInventory();
        UI.print("You are " + player.getName() + "\n" + player.getDescription() + "\n");
        if(!inventory.isEmpty()) {
            UI.print("Inventory: ");
            for (Item item : inventory) {
                UI.print(" - " + item.name() + ": " + item.description());
            }
            UI.print("");
        }
    }

    /**
     * Print a description of the room the given player is currently in.
     * @param player The player.
     */
    public static void look(Player player) {

        UI.print(player.look());
        var itemsInRoom = player.getCurrentRoom().getItems();

        if(!itemsInRoom.isEmpty()) {
            UI.print("You see the following items in the room: \n");
            for (Item item : itemsInRoom) {
                UI.print("  " + item.name() + ": " + item.description());
            }
        }

    }

    /**
     * Take the named item from the room the player is in.  Throws an exception if the item isn't in the room.
     * The item is added to the player's inventory and removed from the room.
     * @param player The player.
     * @param itemName The name of the item to take.
     * @throws ImpossibleCommandException If the item is not in the current room of the player.
     */
    public static void take(Player player, String itemName) throws ImpossibleCommandException {
        Room currentRoom = player.getCurrentRoom(); // get the current room
        var itemsInRoom = currentRoom.getItems(); // get the items in the room

        Item itemToTake = null;  // create a variable to hold the item to take
        for (Item item : itemsInRoom) { // loop through the items in the room
            if (item.name().equals(itemName)) { //
                itemToTake = item;
            }
        }
        if (itemToTake != null) { // if the item is in the room
            player.takeItem(itemToTake); // add item to player inventory
            if(itemToTake.name().equals("key")) {
                player.setHasKey(true);
            }
            player.getCurrentRoom().removeItem(itemToTake); // remove item from room
            UI.print("You have taken the " + itemToTake.name() + ".");
        }
        else {
            throw new ImpossibleCommandException("The item " + itemName + " is not in the room.");
        }
    }
}
