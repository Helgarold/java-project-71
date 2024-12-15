package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]",
            paramLabel = "format",
            defaultValue = "stylish")
    private String format;

    @Parameters(index = "0",
            description = "path to first file",
            paramLabel = "filepath1")
    private String filepath1;

    @Parameters(index = "1",
            description = "path to second file",
            paramLabel = "filepath2")
    private String filepath2;

    @Option(names = {"-h", "--help"},
            usageHelp = true,
            description = "Show this help message and exit.")
    private boolean help;

    @Option(names = {"-V", "--version"},
            versionHelp = true,
            description = "Print version information and exit.")
    private boolean version;

    @Option(names = {"--type"},
            description = "type of files to compare (json or yaml)",
            paramLabel = "type")
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

        try {
            String diff;

            // Определяем тип файла
            if ("json".equalsIgnoreCase(type)) {
                diff = Differ.generate(filepath1, filepath2);
            } else if ("yaml".equalsIgnoreCase(type) || "yml".equalsIgnoreCase(type)) {
                diff = YmlDiffer.generate(filepath1, filepath2);
            } else {
                throw new IllegalArgumentException("Unsupported file type: " + type);
            }

            System.out.println(diff);
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
