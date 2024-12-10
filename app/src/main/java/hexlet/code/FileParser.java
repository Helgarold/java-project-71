package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Метод для чтения файла и преобразования JSON в Map
    public static Map<String, Object> getData(String filePath) throws IOException {
        if (!Files.exists(Paths.get(filePath))) {
            throw new IOException("File not found: " + filePath);
        }

        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return parse(content); // Парсинг содержимого
    }

    // Метод для парсинга JSON
    private static Map<String, Object> parse(String content) throws IOException {
        try {
            return objectMapper.readValue(content, Map.class);
        } catch (JsonParseException e) {
            throw new IOException("Invalid JSON format in file: " + content, e);
        } catch (JsonMappingException e) {
            throw new IOException("Error mapping JSON to object in file: " + content, e);
        }
    }
}
