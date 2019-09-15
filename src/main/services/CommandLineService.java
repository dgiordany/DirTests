package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommandLineService {
    private String programPath = System.getenv("WINDIR") + "\\system32\\cmd.exe /c ";

    /**
     * @param command команда для запуска в консоли Windows
     * @return List<String>
     */
    public List<String> execute(String command) {
        List<String> output = new ArrayList<>();
        String line;
        try {
            Process process = Runtime.getRuntime().exec(programPath + " " + command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
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
