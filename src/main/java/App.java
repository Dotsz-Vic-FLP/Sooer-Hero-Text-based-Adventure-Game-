
import command.Command;
import command.ImpossibleCommandException;
import ui.UI;

import java.util.regex.Pattern;

/**
 * The entry point for the game.  Coordinates the main game loop and the UI.
 */
public class App {

    public static void main(String[] args) {
        UI.init();
        var player = GameInitializer.init();

        UI.title("SOOER HERO");

        // Tell the player where they are to start
        UI.print(player.look());

        // This is a regular expression that matches any of the valid commands.
        final Pattern VALID_COMMAND_REGEX = Pattern.compile("^help|quit|look|status|take\\s+[a-zA-Z-]+|go\\s+[NnEeSsWw]$");

        while ( true ) {
            String[] commandParts = UI.promptForCommand(
                    "What now? (Type 'help' for a list of commands) \n",
                    VALID_COMMAND_REGEX,
                    "Invalid command. Type 'help' for a list of commands."
            );

            try {

                // Call the appropriate method for the command
                switch(commandParts[0]) {
                    case "go" -> Command.go(player, commandParts[1]);
                    case "help" -> Command.help();
                    case "look" -> Command.look(player);
                    case "status" -> Command.status(player);
                    case "take" -> Command.take(player, commandParts[1]);
                    case "quit" -> System.exit(0);
                    default -> {throw new RuntimeException("Unknown command: " + commandParts[0]);}
                }

                // Check if the player has won
                if (player.getCurrentRoom().isEndRoom()){
                    if (player.hasKey()){
                        player.getCurrentRoom().setLock(false);
                        UI.title("You have unlocked the door and won the game!");
                        System.exit(0);
                    }
                }

            } catch (ImpossibleCommandException e) {
                UI.error(e.getMessage());
            }
        }
    }
}
