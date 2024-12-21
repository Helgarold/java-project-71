package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
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
                if (value1 instanceof List || value1 instanceof Map
                        || value2 instanceof List || value2 instanceof Map) {
                    // Можно вызвать генерацию для глубокого сравнения
                    diffNodes.add(new DiffNode(key, "changed", value1, value2));
                } else {
                    diffNodes.add(new DiffNode(key, "changed", value1, value2));
                }
            } else {
                diffNodes.add(new DiffNode(key, "unchanged", value1, value2));
            }
        }

        return diffNodes;
    }

    private static Map<String, Object> getData(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), Map.class);
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
