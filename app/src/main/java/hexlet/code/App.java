package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.Map;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    private boolean help;

    @Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean version;

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        try {
            Map<String, Object> data1 = FileParser.getData(filepath1);
            Map<String, Object> data2 = FileParser.getData(filepath2);
            // Здесь вы можете добавить логику сравнения data1 и data2

            System.out.printf("Comparing %s and %s with format %s%n", filepath1, filepath2, format);
            System.out.println("Data from first file: " + data1);
            System.out.println("Data from second file: " + data2);
        } catch (IOException e) {
            System.err.printf("Error reading files: %s%n", e.getMessage());
        } catch (Exception e) {
            System.err.printf("An unexpected error occurred: %s%n", e.getMessage());
        }
    }
}
