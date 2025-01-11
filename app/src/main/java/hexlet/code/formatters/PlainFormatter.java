package hexlet.code.formatters;

import hexlet.code.DiffNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class PlainFormatter extends Formatter {

    private final ObjectMapper objectMapper = new ObjectMapper(); // Инициализация ObjectMapper для работы с JSON

    @Override
    public String format(List<DiffNode> diffNodes) {
        try {
            return objectMapper.writeValueAsString(diffNodes); // Прямое преобразование в JSON
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting to JSON: " + e.getMessage(), e);
        }
    }
}
