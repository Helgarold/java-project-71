package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;


public class AppTest {

    @Test
    public void testHelp() {
        String[] args = {"-h"};
        App app = new App();
        CommandLine.run(app, args);
        // Здесь можно проверить вывод на наличие строки помощи
        // Например, перенаправить System.out и проверить содержимое
    }

    @Test
    public void testVersion() {
        String[] args = {"-V"};
        App app = new App();
        CommandLine.run(app, args);
        // Здесь можно проверить вывод на наличие версии
        // Например, перенаправить System.out и проверить содержимое
    }

    @Test
    public void testJsonDiff() {
        String[] args = {"path/to/first.json", "path/to/second.json", "--type=json"};
        App app = new App();
        CommandLine.run(app, args);
        // Здесь нужно проверить вывод, предположительно по стандартному выводу
        // Например, перенаправить System.out и проверить круглую разницу
    }

    @Test
    public void testYamlDiff() {
        String[] args = {"path/to/first.yaml", "path/to/second.yaml", "--type=yaml"};
        App app = new App();
        CommandLine.run(app, args);
        // Проверить вывод аналогично предыдущему тесту
    }

    @Test
    public void testUnsupportedFileType() {
        String[] args = {"path/to/first.txt", "path/to/second.txt", "--type=txt"};
        App app = new App();
        CommandLine.run(app, args);
        // Проверить вывод на наличие сообщения об ошибке "Unsupported file type: txt"
        // Перенаправить стандартный вывод и проверить его
    }

    @Test
    public void testIOException() {
        String[] args = {"invalid/path/to/first.json", "invalid/path/to/second.json"};
        App app = new App();
        CommandLine.run(app, args);
        // Проверить вывод на наличие сообщения об ошибке "Error reading files: ..."
        // Перенаправить стандартный вывод и проверить его
    }
}
