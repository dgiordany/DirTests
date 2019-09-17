package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandLineService {
    private static final String APPLICATION_PATH = System.getenv("WINDIR") +
        File.separator + "system32" + File.separator + "cmd.exe /c ";

    /**
     * @param command команда для запуска в консоли Windows
     * @return List<String>
     */
    public List<String> execute(String command) {
        List<String> output = new ArrayList<>();
        String line;
        try {
            Process process = Runtime.getRuntime().exec(APPLICATION_PATH + " " + command);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), "CP866"));
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }
            reader.close();
            process.waitFor();
        } catch (IOException | NullPointerException | InterruptedException e) {
            e.printStackTrace();
        }

        return output;
    }
}
