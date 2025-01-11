package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter extends Formatter {

    @Override
    public String format(List<DiffNode> diffNodes, String formatType) {
        // Проверяем, что формат типа "json"
        if (!"json".equalsIgnoreCase(formatType)) {
            throw new IllegalArgumentException("JsonFormatter only supports 'json' format type");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> resultMap = new LinkedHashMap<>();

        for (DiffNode node : diffNodes) {
            String key = node.getKey();
            switch (node.getType()) {
                case "added":
                    resultMap.put(key, node.getNewValue());
                    break;
                case "removed":
                    resultMap.put(key, null); // Удаленные значения можно представлять как null
                    break;
                case "updated":
                    resultMap.put(key, Map.of("oldValue", node.getOldValue(), "newValue", node.getNewValue()));
                    break;
                case "unchanged":
                    resultMap.put(key, node.getOldValue());
                    break;
                case "changed":
                    resultMap.put(key, Map.of("oldValue", node.getOldValue(), "newValue", node.getNewValue()));
                    break;
                default:
                    break;
            }
        }

        // Конвертируем результат в JSON
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while formatting to JSON", e);
        }
    }
}
