package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testGenerateWithIdenticalFiles() throws IOException {
        List<DiffNode> diffs = Differ.generate("src/main/resources/file1.json", "src/main/resources/file1.json");

        // Проверяем, что список не пуст, так как есть "unchanged" записи
        assertFalse(diffs.isEmpty(), "Список не должен быть пустым при сравнении одинаковых файлов");

        // Проверяем, что все записи имеют тип "unchanged"
        assertTrue(diffs.stream().allMatch(diff -> "unchanged".equals(diff.getType())),
                "Все ключи должны быть помечены как 'unchanged'");
    }

    @Test
    public void testGenerateWithDifferentFiles() throws IOException {
        List<DiffNode> diffs = Differ.generate("src/main/resources/file1.json", "src/main/resources/file2.json");

        // Проверяем, что ключ host unchanged
        assertTrue(diffs.stream().anyMatch(diff -> diff.getKey().equals("host") && diff.getType().equals("unchanged")),
                "Ключ 'host' должен остаться без изменений");

        // Проверяем, что ключ timeout изменился
        assertTrue(diffs.stream().anyMatch(diff -> diff.getKey().equals("timeout") && diff.getType().equals("changed")),
                "Значение ключа 'timeout' должно измениться");

        // Проверяем, что ключ proxy был удалён
        assertTrue(diffs.stream().anyMatch(diff -> diff.getKey().equals("proxy") && diff.getType().equals("removed")),
                "Ключ 'proxy' должен быть удалён");

        // Проверяем, что ключ follow был удалён
        assertTrue(diffs.stream().anyMatch(diff -> diff.getKey().equals("follow") && diff.getType().equals("removed")),
                "Ключ 'follow' должен быть удалён");

        // Проверяем, что ключ verbose был добавлен
        assertTrue(diffs.stream().anyMatch(diff -> diff.getKey().equals("verbose") && diff.getType().equals("added")),
                "Ключ 'verbose' должен быть добавлен");
    }

    @Test
    public void testGenerateWithOnlyAddedAndRemovedKeys() throws IOException {
        // Выполняем тест на file1.json и file2.json
        List<DiffNode> diffs = Differ.generate("src/main/resources/file1.json", "src/main/resources/file2.json");

        assertEquals(5, diffs.size(), "Должно быть 5 изменений между двумя файлами.");
    }

    @Test
    public void testGenerateWithYAMLFiles() throws IOException {
        List<DiffNode> diffs = Differ.generate("src/main/resources/file1.yml", "src/main/resources/file2.yml");
        assertTrue(diffs.stream().anyMatch(diff -> diff.getType().equals("added")),
                "Должен быть добавленный ключ в YAML");
    }
}
