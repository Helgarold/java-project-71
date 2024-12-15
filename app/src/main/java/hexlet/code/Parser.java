package hexlet.code;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    // Метод для чтения YAML-файла и преобразования его в Map
    public static Map<String, Object> getData(String filePath) throws IOException {
        // Проверка существования файла
        if (!Files.exists(Paths.get(filePath))) {
            throw new IOException("File not found: " + filePath);
        }

        // Определение типа файла по расширению
        String fileExtension = getFileExtension(filePath);
        if (!"yml".equalsIgnoreCase(fileExtension) && !"yaml".equalsIgnoreCase(fileExtension)) {
            throw new IOException("Unsupported file type: " + fileExtension);
        }

        try (InputStream inputStream = new FileInputStream(filePath)) {
            Yaml yaml = new Yaml();
            return yaml.load(inputStream); // Парсинг содержимого
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filePath, e);
        }
    }

    // Новый метод для загрузки данных из строки YAML
    public static Map<String, Object> getDataFromString(String ymlString) {
        Yaml yaml = new Yaml();
        return yaml.load(ymlString); // Парсинг содержимого из строки
    }

    // Вспомогательный метод для получения расширения файла
    private static String getFileExtension(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf('.');
        return (lastIndexOfDot == -1) ? "" : filePath.substring(lastIndexOfDot + 1);
    }
}
