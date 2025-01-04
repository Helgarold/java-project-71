package hexlet.code;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class YmlDiffer {

    public static List<DiffNode> generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> data1 = Parser.getData(filePath1);
        Map<String, Object> data2 = Parser.getData(filePath2);

        return compareData(data1, data2);
    }

    public static List<DiffNode> generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object> data1 = Parser.getData(filePath1);
        Map<String, Object> data2 = Parser.getData(filePath2);

        List<DiffNode> diffNodes = compareData(data1, data2);

        return diffNodes; // В будущем, если добавите форматирование, возвращайте отформатированный результат
    }

    private static List<DiffNode> compareData(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        List<DiffNode> diffNodes = new ArrayList<>();

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (value1 == null && value2 != null) {
                diffNodes.add(new DiffNode(key, "added", null, value2));
            } else if (value1 != null && value2 == null) {
                diffNodes.add(new DiffNode(key, "removed", value1, null));
            } else if (value1 != null && value2 != null && !value1.equals(value2)) {
                diffNodes.add(new DiffNode(key, "changed", value1, value2));
            } else if (value1 != null && value2 != null && value1.equals(value2)) {
                diffNodes.add(new DiffNode(key, "unchanged", value1, value2));
            }
        }

        return diffNodes;
    }
}
