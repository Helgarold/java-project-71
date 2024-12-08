package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean help;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean version;

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        if (version) {
            System.out.println("Version 1.0");
        }
        // Здесь можно будет добавить дальнейшую логику
    }
}
