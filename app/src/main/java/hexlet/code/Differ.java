package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
                isFirstEntry = false; // Установить флаг после первого элемента
            } else if (value1 == null && value2 != null) {
                if (!isFirstEntry) {
                    result.append("\n"); // Добавить пустую строку перед следующей парой
                }
                result.append(String.format("  + %s: %s", key, value2)); // Added keys
                isFirstEntry = false; // Установить флаг после первого элемента
            } else if (value1 != null && value2 != null && !value1.equals(value2)) {
                if (!isFirstEntry) {
                    result.append("\n"); // Добавить пустую строку перед следующей парой
                }
                result.append(String.format("  - %s: %s", key, value1)); // Modified keys
                result.append(String.format("\n  + %s: %s", key, value2)); // Modified keys
                isFirstEntry = false; // Установить флаг после первого элемента
            } else if (value1 != null && value2 != null && value1.equals(value2)) {
                if (!isFirstEntry) {
                    result.append("\n"); // Добавить пустую строку перед следующей парой
                }
                result.append(String.format("%s: %s", key, value1)); // Both values are the same
                isFirstEntry = false; // Установить флаг после первого элемента
            }
        }

        result.append("\n}"); // Закрывающая фигурная скобка с переносом строки перед ней
        return result.toString();
    }
}
