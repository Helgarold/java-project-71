package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

public class YmlDifferTest {

    @Test
    void testCompareSameValues() throws IOException {
        String filePath1 = "src/test/resources/file1.yml";
        String filePath2 = "src/test/resources/file1.yml"; // Сравню сам с собой

        String result = YmlDiffer.generate(filePath1, filePath2);

        // Ожидаем, что файлы идентичны, поэтому результат должен быть пустым
        assertEquals("{\n}", result);
    }

    @Test
    void testCompareDifferentValues() throws IOException {
        String filePath1 = "src/test/resources/file1.yml";
        String filePath2 = "src/test/resources/file2.yml";

        String result = YmlDiffer.generate(filePath1, filePath2);

        // Ожидаем, что результат будет не пустым, так как значения отличаются
        assertFalse(result.isEmpty());
    }

    @Test
    void testCompareDifferentKeys() throws IOException {
        String filePath1 = "src/test/resources/file1.yml";
        String filePath2 = "src/test/resources/file2.yml";

        String result = YmlDiffer.generate(filePath1, filePath2);

        // Ожидаем, что результат будет не пустым, так как есть различия в ключах
        assertFalse(result.isEmpty());
    }
}
