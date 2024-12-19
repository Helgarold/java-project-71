package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void testGetDataFromJsonFile() throws IOException {
        String jsonFilePath = "src/test/resources/test.json"; // Убедитесь, что файл существует
        Map<String, Object> data = Parser.getData(jsonFilePath);
        assertNotNull(data);
        assertEquals("value", data.get("key")); // Замените на ваши ожидаемые значения
    }

    @Test
    public void testFileNotFound() {
        Exception exception = assertThrows(IOException.class, () -> {
            Parser.getData("nonexistent.file");
        });
        assertEquals("File not found: nonexistent.file", exception.getMessage());
    }

    @Test
    public void testGetDataFromString() {
        String yamlString = "key: value";
        Map<String, Object> data = Parser.getDataFromString(yamlString);
        assertNotNull(data);
        assertEquals("value", data.get("key"));
    }
}
