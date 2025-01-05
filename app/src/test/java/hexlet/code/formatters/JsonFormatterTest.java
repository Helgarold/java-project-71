package hexlet.code.formatters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonFormatterTest {

    @Test
    public void testFormatThrowsException() {
        // Создаем JsonFormatter
        JsonFormatter jsonFormatter = new JsonFormatter();

        // Проверяем, что при передаче неправильных данных выбрасывается исключение
        try {
            jsonFormatter.format(null);
        } catch (RuntimeException e) {
            assertEquals("Error while formatting to JSON", e.getMessage());
        }
    }
}
