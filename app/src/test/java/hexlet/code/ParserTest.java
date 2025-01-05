package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserTest {
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Создаем временный файл для тестирования
        tempFile = Files.createTempFile("test-", ".yml");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Удаляем временный файл после тестирования
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testGetDataFromYamlFile() throws IOException {
        // Записываем данные YAML во временный файл
        String yamlContent = "key: value";
        Files.write(tempFile, yamlContent.getBytes());

        // Получаем данные из временного файла
        Map<String, Object> data = Parser.getData(tempFile.toString());

        // Проверяем, что данные не нулевые и содержат ожидаемое значение
        assertNotNull(data);
        assertEquals("value", data.get("key"));
    }
}
