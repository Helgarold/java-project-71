package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;
import java.util.Map;

public class StylishFormatter extends Formatter {

    @Override
    public String format(List<DiffNode> diffNodes, String formatType) {
        // Проверяем, что формат типа "stylish"
        if (!"stylish".equalsIgnoreCase(formatType)) {
            throw new IllegalArgumentException("StylishFormatter only supports 'stylish' format type");
        }

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (DiffNode node : diffNodes) {
            switch (node.getType()) {
                case "added":
                    result.append(String.format("  + %s: %s\n", node.getKey(), formatValue(node.getNewValue())));
                    break;
                case "removed":
                    result.append(String.format("  - %s: %s\n", node.getKey(), formatValue(node.getOldValue())));
                    break;
                case "changed":
                    result.append(String.format("  - %s: %s\n", node.getKey(), formatValue(node.getOldValue())));
                    result.append(String.format("  + %s: %s\n", node.getKey(), formatValue(node.getNewValue())));
                    break;
                case "unchanged":
                    result.append(String.format("    %s: %s\n", node.getKey(), formatValue(node.getOldValue())));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown node type: " + node.getType());
            }
        }

        result.append("}");
        return result.toString();
    }

    private String formatValue(Object value) {
        // Аналогично методу в PlainFormatter, если не хотите дублировать код, можете вынести его в Formatter
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        }
        return String.valueOf(value);
    }
}
