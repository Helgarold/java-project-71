package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> getData(String filePath) throws IOException {
        if (!Files.exists(Paths.get(filePath))) {
            throw new IOException("File not found: " + filePath);
        }

        String fileExtension = getFileExtension(filePath);
        switch (fileExtension.toLowerCase()) {
            case "json":
                return getDataFromFile(filePath, JSON_MAPPER);
            case "yml":
            case "yaml":
                return getDataFromFile(filePath, YAML_MAPPER);
            default:
                throw new IOException("Unsupported file type: " + fileExtension);
        }
    }

    private static Map<String, Object> getDataFromFile(String filePath, ObjectMapper mapper) throws IOException {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return mapper.readValue(content, Map.class);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filePath + " - " + e.getMessage(), e);
        }
    }

    private static String getFileExtension(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf('.');
        return (lastIndexOfDot == -1) ? "" : filePath.substring(lastIndexOfDot + 1);
    }
}
