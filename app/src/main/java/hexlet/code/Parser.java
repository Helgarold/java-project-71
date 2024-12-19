package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Yaml YAML = new Yaml();

    // Метод для чтения файла и преобразования его в Map
    public static Map<String, Object> getData(String filePath) throws IOException {
        if (!Files.exists(Paths.get(filePath))) {
            throw new IOException("File not found: " + filePath);
        }

        String fileExtension = getFileExtension(filePath);
        switch (fileExtension.toLowerCase()) {
            case "json":
                return getDataFromJsonFile(filePath);
            case "yml":
            case "yaml":
                return getDataFromYamlFile(filePath);
            default:
                throw new IOException("Unsupported file type: " + fileExtension);
        }
    }

    // Метод для чтения JSON-файла и преобразования его в Map
    private static Map<String, Object> getDataFromJsonFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return parseJson(content);
    }

    // Метод для парсинга JSON
    private static Map<String, Object> parseJson(String content) throws IOException {
        try {
            return OBJECT_MAPPER.readValue(content, Map.class);
        } catch (JsonParseException e) {
            throw new IOException("Invalid JSON format in file: " + content, e);
        } catch (JsonMappingException e) {
            throw new IOException("Error mapping JSON to object in file: " + content, e);
        }
    }

    private static Map<String, Object> getDataFromYamlFile(String filePath) throws IOException {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Map<String, Object> data = YAML.load(inputStream);
            if (data == null) {
                throw new IOException("YAML file contains invalid data: " + filePath);
            }
            return data; // Возврат разобранного содержимого
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filePath, e);
        }
    }

    // Новый метод для загрузки данных из строки YAML
    public static Map<String, Object> getDataFromString(String ymlString) {
        return YAML.load(ymlString); // Парсинг содержимого из строки
    }

    // Вспомогательный метод для получения расширения файла
    private static String getFileExtension(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf('.');
        return (lastIndexOfDot == -1) ? "" : filePath.substring(lastIndexOfDot + 1);
    }
}
