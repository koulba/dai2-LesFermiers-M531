package core;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
    public static final String SAVE_FILENAME = "savefile.txt";

    public static void saveCommands(List<String> commandHistory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILENAME))) {
            for (String command : commandHistory) {
                writer.write(command);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAndReplayCommands(Game game) {
        try {
            List<String> commands = Files.readAllLines(Paths.get(SAVE_FILENAME));
            for (String command : commands) {
                game.executeCommand(command);
            }
            System.out.println("Save loaded successfully.");
        } catch (IOException e) {
            System.out.println("Failed to load the save file.");
            e.printStackTrace();
        }
    }
}

