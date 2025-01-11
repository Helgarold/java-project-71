package hexlet.code.formatters;

import hexlet.code.DiffNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainFormatter extends Formatter {
    @Override
    public String format(List<DiffNode> diffNodes, String formatType) {
        if ("json".equalsIgnoreCase(formatType)) {
            return formatToJson(diffNodes);
        }

        return formatToPlain(diffNodes);
    }

    private String formatToPlain(List<DiffNode> diffNodes) {
        StringBuilder result = new StringBuilder();

        for (DiffNode node : diffNodes) {
            String key = node.getKey();
            String type = node.getType();
            String value;

            switch (type) {
                case "added":
                    value = node.getFormattedValues();
                    result.append(String.format("Property '%s' was added with %s%n", key, value));
                    break;
                case "removed":
                    result.append(String.format("Property '%s' was removed%n", key));
                    break;
                case "updated":
                    String formattedValues = node.getFormattedValues();
                    String[] values = formattedValues.split("to: ");
                    result.append(String.format("Property '%s' was updated. From %s to %s%n",
                            key, values[0].replace("From: ", "").trim(), values[1].trim()));
                    break;
                default:
                    break;
            }
        }

        return result.toString().trim();
    }

    private String formatToJson(List<DiffNode> diffNodes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, Object>> jsonFormatList = diffNodes.stream()
                    .map(node -> Map.of(
                            "key", node.getKey(),
                            "type", node.getType(),
                            "oldValue", node.getOldValue(),
                            "newValue", node.getNewValue()
                    ))
                    .collect(Collectors.toList());

            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonFormatList);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert to JSON", e);
        }
    }
}
