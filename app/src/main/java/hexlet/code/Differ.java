package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;
import hexlet.code.formatters.Formatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static List<DiffNode> generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        List<DiffNode> diffNodes = new ArrayList<>();

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (value1 == null && value2 == null) {
                diffNodes.add(new DiffNode(key, "unchanged", null, null));
            } else if (value1 == null) {
                diffNodes.add(new DiffNode(key, "added", null, value2));
            } else if (value2 == null) {
                diffNodes.add(new DiffNode(key, "removed", value1, null));
            } else if (!value1.equals(value2)) {
                diffNodes.add(new DiffNode(key, "changed", value1, value2));
            } else {
                diffNodes.add(new DiffNode(key, "unchanged", value1, value2));
            }
        }

        return diffNodes;
    }

    private static Map<String, Object> getData(String filePath) throws IOException {
        if (filePath.endsWith(".json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), Map.class);
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            Yaml yaml = new Yaml();
            try (InputStream inputStream = new FileInputStream(new File(filePath))) {
                return yaml.load(inputStream);
            }
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }

    // Новый метод для генерации с форматированием
    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        List<DiffNode> diffNodes = generate(filePath1, filePath2);
        Formatter formatter = Formatter.getFormatter(formatName);
        return formatter.format(diffNodes, formatName); // Теперь правильно используется метод форматирования
    }

    public static boolean compare(String json1, String json2) {
        Map<String, Object> data1 = getDataFromJsonString(json1);
        Map<String, Object> data2 = getDataFromJsonString(json2);
        return data1.equals(data2);
    }

    private static Map<String, Object> getDataFromJsonString(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
