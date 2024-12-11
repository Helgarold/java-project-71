package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import com.fasterxml.jackson.databind.ObjectMapper;

import static hexlet.code.FileParser.getData;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        boolean isFirstEntry = true; // Флаг для первого элемента

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (value1 != null && value2 == null) {
                if (!isFirstEntry) {
                    result.append("\n"); // Добавить пустую строку перед следующей парой
                }
                result.append(String.format("  - %s: %s", key, value1)); // Deleted keys
                isFirstEntry = false;
            } else if (value1 == null && value2 != null) {
                if (!isFirstEntry) {
                    result.append("\n");
                }
                result.append(String.format("  + %s: %s", key, value2)); // Added keys
                isFirstEntry = false;
            } else if (value1 != null && value2 != null && !value1.equals(value2)) {
                if (!isFirstEntry) {
                    result.append("\n");
                }
                result.append(String.format("  - %s: %s", key, value1)); // Modified keys
                result.append(String.format("\n  + %s: %s", key, value2)); // Modified keys
                isFirstEntry = false;
            } else if (value1 != null && value2 != null && value1.equals(value2)) {
                if (!isFirstEntry) {
                    result.append("\n");
                }
                result.append(String.format("  %s: %s", key, value1)); // Both values are the same
                isFirstEntry = false;
            }
        }

        if (!isFirstEntry) {
            result.append("\n"); // Добавить перенос строки перед закрывающей фигурной скобкой
        }
        result.append("}"); // Закрывающая фигурная скобка
        return result.toString();
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
