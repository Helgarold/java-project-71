package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean help;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean version;

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        // Здесь будет логика для сравнения и вывода результата, пока она не реализована
        System.out.printf("Comparing %s and %s with format %s%n", filepath1, filepath2, format);
    }
}
