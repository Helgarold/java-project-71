package hexlet.code;

import hexlet.code.formatters.Formatter;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.List;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]",
            paramLabel = "format",
            defaultValue = "stylish")
    private String format;

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String filepath2;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean help;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean version;

    @Option(names = {"--type"}, description = "type of files to compare (json or yaml)", paramLabel = "type")
    private String type;

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        if (help) {
            CommandLine.usage(this, System.out);
            return;
        }

        if (version) {
            System.out.println("Version 1.0");
            return;
        }

        // Отладочные сообщения
        System.out.println("Filepath 1: " + filepath1);
        System.out.println("Filepath 2: " + filepath2);
        System.out.println("Format: " + format);
        System.out.println("Type: " + type);

        // Проверка типа файла
        if (type == null) {
            System.err.println("Error: Type must be specified. Use --type to specify it.");
            return;
        }

        try {
            // Получаем список различий
            List<DiffNode> diffNodes = Differ.generate(filepath1, filepath2);

            // Получаем форматированный вывод, передавая формат как второй параметр
            String output = Formatter.getFormatter(format).format(diffNodes, format);
            System.out.println(output);

        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
            e.printStackTrace(); // Дополнительная отладочная информация
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Дополнительная отладочная информация
        }
    }
}
