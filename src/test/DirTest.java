import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import services.CommandLineService;

public class DirTest {
    private String dirCommand = "dir /s/b ";

    @DataProvider(name = "directories")
    Object[] getDirectory() {
        return new Object[] {
//                System.getProperty("user.dir") + File.separator + "src",
                System.getProperty("user.home") + File.separator + "Pictures",
                System.getenv("windir") + File.separator + "Help"
        };
    }

    /*
        Получаем список файлов из переданной директории с помощью dir.
        Рекурсивно получаем список файлов с помощью средств Java.
        Сравниваем количество строк в массивах и их содержимое.
    */
    @Test(dataProvider = "directories")
    void testDirOutput(String directoryPath) {
        List<String> actualPaths = new CommandLineService().execute(dirCommand + directoryPath);
        List<String> expectedPaths = getAllFilesFromDirectory(directoryPath);

        assert actualPaths.size() == expectedPaths.size();
        assert expectedPaths.containsAll(actualPaths);
    }

    /*
        Берем список файлов из директории.
        Создаем новый файл в двух новых директориях, вложенных друг в друга.
        Проверяем, что количество файлов стало на три больше.
        Удаляем файл и созданные папки, проверяем, что количество файлов стало на один меньше.
    */
    @Test
    void testChangeFilesSizeInDirectory() {
        CommandLineService commandService = new CommandLineService();
        String fileName = File.separator + "privet.txt";
        String dirOne = File.separator + "one";
        String dirTwo = File.separator + "two";
        String directoryPath = System.getProperty("user.home") + File.separator + "Pictures";
        int firstFilePaths = commandService.execute(dirCommand + directoryPath).size();

        commandService.execute("mkdir " + directoryPath + dirOne + dirTwo);
        commandService.execute("type nul > " + directoryPath + dirOne + dirTwo + fileName);
        assert commandService.execute(dirCommand + directoryPath).size() == firstFilePaths + 3;

        commandService.execute("rmdir /q/s " + directoryPath + dirOne);
        assert firstFilePaths == commandService.execute(dirCommand + directoryPath).size();
    }

    /*
        Пытаемся прочитать список файлов и папок из несуществующей директории.
        Количество полученных строк должно быть равно нулю.
    */
    @Test
    void testNotExistingFolder() {
        CommandLineService commandService = new CommandLineService();
        List<String> output = commandService.execute(dirCommand + "yamalenkayaloshadka");
        assert output.size() == 0;
    }

    private List<String> getAllFilesFromDirectory(String directoryPath) {
        List<String> directories = new ArrayList<>();
        String path;
        File[] list = new File(directoryPath).listFiles();
        for (File file : list) {
            path = file.getPath();
            if (!file.isHidden()) {
                directories.add(path);
            }

            if (file.isDirectory()) {
                directories.addAll(getAllFilesFromDirectory(path));
            }
        }

        return directories;
    }

    private List<String> getAllFilesFromDirectory(String directoryPath, boolean showHidden) {
        List<String> directories = new ArrayList<>();
        String path;
        File[] list = new File(directoryPath).listFiles();
        for (File file : list) {
            path = file.getPath();
            directories.add(path);
            if (file.isDirectory()) {
                directories.addAll(getAllFilesFromDirectory(path, showHidden));
            }
        }

        return directories;
    }
}
