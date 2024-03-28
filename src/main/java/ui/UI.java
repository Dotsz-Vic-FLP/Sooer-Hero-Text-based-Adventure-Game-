package ui;

import com.github.tomaslanger.chalk.Chalk;
import org.fusesource.jansi.AnsiConsole;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class provides a simple way to display text to the user.
 * It also provides a simple way to prompt the user for input.
 */
public class UI {

    /**
     * This method initializes the UI class and must be called before any other methods in this class.
     * It enables colors in the console output. {@see the external {@link <a href="https://github.com/tomas-langer/chalk">chalk</a> library used by this class.}}
     */
    public static void init() {
        System.setProperty("jansi.passthrough", "true");
        AnsiConsole.systemInstall();
    }

    /**
     * This method displays a title in the console.
     * @param s The title to display.
     */
    public static void title(String s) {
        System.out.println(Chalk.on(s).bgGreen().black());
    }

    /**
     * This method displays a message in the console.
     * @param s The message to display.
     */
    public static void print(String s) {
        System.out.println(Chalk.on(s).green());
    }

    /**
     * Repeatedly prompts the user for input until a valid command is entered.
     * @param prompt The prompt to display to the user.
     * @param validCommandRegex A regular expression that the user's input must match.
     * @param invalidCommandMessage The message to display to the user if the input does not match the regular expression.
     * @return An array of strings containing the user's input split on whitespace.
     */
    public static String[] promptForCommand(String prompt, Pattern validCommandRegex, String invalidCommandMessage) {

        Scanner scanner = new Scanner(System.in);

        while ( true ) {
            System.out.print(Chalk.on(prompt));
            var response = scanner.nextLine().strip();

            if ( response.equals("") ) {
                continue;
            }

            if ( ! validCommandRegex.matcher(response).matches() ) {
                error(invalidCommandMessage);
                continue;
            }

            var parts = response.split("\\s+");

            return parts;

        }
    }

    /**
     * Displays a help message to the user.
     * @param heading The heading to display.
     * @param preamble The preamble to display.
     * @param messages A map of command names to descriptions.
     */
    public static void help(String heading, String preamble, Map<String, String> messages) {
        System.out.println();
        System.out.println(Chalk.on(heading).underline().yellow());
        if ( preamble != null && preamble.strip().length() > 0 ) {
            System.out.println();
            System.out.println(Chalk.on(preamble).yellow());
            System.out.println();
        }
        for ( var entry : messages.entrySet() ) {
            System.out.println(Chalk.on(entry.getKey()).bold() + " - " + entry.getValue());
        }
        System.out.println();
    }

    /**
     * Displays an error message to the user.
     * @param s The error message to display.
     */
    public static void error(String s) {
        System.out.println(Chalk.on(s).red());
    }
}
