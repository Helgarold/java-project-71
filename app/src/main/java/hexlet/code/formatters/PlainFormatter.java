package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;
import java.util.Map;

public class PlainFormatter extends Formatter {  // Наследование от Formatter

    @Override
    public String format(List<DiffNode> diffNodes) {  // Переопределение метода format
        StringBuilder result = new StringBuilder();
        for (DiffNode node : diffNodes) {
            String line = formatNode(node);
            if (line != null) {
                result.append(line).append("\n");
            }
        }
        return result.toString().trim();
    }

    private String formatNode(DiffNode node) {
        StringBuilder sb = new StringBuilder();
        String action = node.getType();  // Извлечение типа изменения
        String property = node.getKey();  // Извлечение ключа
        Object oldValue = node.getOldValue();
        Object newValue = node.getNewValue();

        switch (action) {
            case "changed":
                sb.append(String.format("Property '%s' was updated. From %s to %s", property,
                        formatValue(oldValue),
                        formatValue(newValue)));
                break;
            case "removed":
                sb.append(String.format("Property '%s' was removed", property));
                break;
            case "added":
                sb.append(String.format("Property '%s' was added with value: %s", property, formatValue(newValue)));
                break;
            case "unchanged":  // Этот случай можно оставить пустым, если он не нужен
                break;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
        return sb.toString(); // Возвращение отформатированной строки
    }

    private String formatValue(Object value) {
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
