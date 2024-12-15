package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.io.IOException;

public class YmlDifferTest {

    @Test
    void testCompareSameValues() {
        String filePath1 = "src/test/resources/file1.yml";
        String filePath2 = "src/test/resources/file2.yml"; // Сравниваем file1.yml с file2.yml

        assertTrue(new File(filePath1).exists(), "file1.yml does not exist");
        assertTrue(new File(filePath2).exists(), "file2.yml does not exist");

        try {
            String result = YmlDiffer.generate(filePath1, filePath2);
            // Ожидаем, что результат будет не пустым, так как файлы разные
            assertFalse(result.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    void testCompareDifferentValues() {
        String filePath1 = "src/test/resources/file1.yml";
        String filePath2 = "src/test/resources/file2.yml";

        assertTrue(new File(filePath1).exists(), "file1.yml does not exist");
        assertTrue(new File(filePath2).exists(), "file2.yml does not exist");

        try {
            String result = YmlDiffer.generate(filePath1, filePath2);
            // Логируем результат для отладки
            System.out.println("Result of comparing different values: " + result);
            assertFalse(result.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    void testCompareDifferentKeys() {
        String filePath1 = "src/test/resources/file1.yml";
        String filePath2 = "src/test/resources/file2.yml";

        assertTrue(new File(filePath1).exists(), "file1.yml does not exist");
        assertTrue(new File(filePath2).exists(), "file2.yml does not exist");

        try {
            String result = YmlDiffer.generate(filePath1, filePath2);
            // Ожидаем, что результат будет не пустым, так как есть различия в ключах
            assertFalse(result.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred: " + e.getMessage());
        }
    }
}
