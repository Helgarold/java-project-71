package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;

public abstract class Formatter {
    public static Formatter getFormatter(String format) {
        switch (format) {
            case "plain":
                return new PlainFormatter();
            case "stylish":
                return new StylishFormatter();
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }

    public abstract String format(List<DiffNode> diffNodes);
}
