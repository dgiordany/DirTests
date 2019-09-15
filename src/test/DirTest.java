import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import services.CommandLineService;

import static org.assertj.core.api.Assertions.assertThat;

public class DirTest {
    private String dirCommand = "dir /s /b ";

    @DataProvider(name = "directories")
    Object[] getDirectory() {
        return new Object[] {
                System.getProperty("user.dir") + File.separator + "src",
                "C:\\Users\\Public\\Pictures"
        };
    }

    @Test(dataProvider = "directories")
    void testDirTest(String directory) {
        List<String> actualOutput = new CommandLineService().executeCommand(dirCommand + directory);
        List<String> expectedOutput = getAllFilesFromDIrectory();

        assertThat(actualOutput).as("");
    }

    private List<String> getAllFilesFromDIrectory() {
        List<String> directories = new ArrayList<>();

        return directories;
    }
}
