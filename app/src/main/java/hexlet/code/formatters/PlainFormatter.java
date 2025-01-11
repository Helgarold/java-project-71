package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;
import java.util.Map;

public class PlainFormatter extends Formatter {

    @Override
    public String format(List<DiffNode> diffNodes) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (int i = 0; i < diffNodes.size(); i++) {
            DiffNode node = diffNodes.get(i);
            String line = formatNode(node);
            if (line != null) {
                result.append(line);
                if (i < diffNodes.size() - 1) {
                    result.append("\n");
                }
            }
        }
        result.append("\n}");
        return result.toString();
    }

    private String formatNode(DiffNode node) {
        StringBuilder sb = new StringBuilder();
        String action = node.getType();
        String property = node.getKey();
        Object oldValue = node.getOldValue();
        Object newValue = node.getNewValue();

        switch (action) {
            case "updated":
            case "changed":
                appendUpdated(sb, property, oldValue, newValue);
                break;
            case "removed":
                appendRemoved(sb, property, oldValue);
                break;
            case "added":
                appendAdded(sb, property, newValue);
                break;
            case "unchanged":
                return null; // ничего не делать
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }

        return sb.toString().trim(); // Убираем лишние пробелы и переводы строк
    }

    private void appendUpdated(StringBuilder sb, String property, Object oldValue, Object newValue) {
        sb.append(String.format("- %s: %s\n", property, formatValue(oldValue)));  // Убираем пробел перед '-'
        sb.append(String.format("+ %s: %s\n", property, formatValue(newValue))); // Убираем пробел перед '+'
    }

    private void appendRemoved(StringBuilder sb, String property, Object oldValue) {
        sb.append(String.format("- %s: %s", property, formatValue(oldValue)));
    }

    private void appendAdded(StringBuilder sb, String property, Object newValue) {
        sb.append(String.format("+ %s: %s", property, formatValue(newValue)));
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return String.valueOf(value); // Без кавычек
        }
        if (value instanceof List) {
            return formatList((List<?>) value);
        }
        if (value instanceof Map) {
            return formatMap((Map<?, ?>) value);
        }
        return String.valueOf(value);
    }

    private String formatList(List<?> list) {
        return "[" + String.join(", ", list.stream().map(this::formatValue).toArray(String[]::new)) + "]";
    }

    private String formatMap(Map<?, ?> map) {
        StringBuilder result = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!first) {
                result.append(", ");
            }
            result.append(entry.getKey()).append("=").append(formatValue(entry.getValue()));
            first = false;
        }
        result.append("}");
        return result.toString();
    }
}
