package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;

import java.util.List;

public class JsonFormatter extends Formatter {

    @Override
    public String format(List<DiffNode> diffNodes) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Конвертируем каждый DiffNode в более простой формат для JSON
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(diffNodes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while formatting to JSON", e);
        }
    }
}
