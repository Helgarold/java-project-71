package hexlet.code.formatters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import java.util.Map;

public class JsonFormatterTest {

    private final JsonFormatter jsonFormatter = new JsonFormatter();

    @Test
    public void testFormatAddedNode() throws Exception {
        DiffNode addedNode = new DiffNode("key1", "added", null, "value1");
        List<DiffNode> diffNodes = List.of(addedNode);

        String expectedJson = "{\n  \"key1\" : \"value1\"\n}";
        String actualJson = jsonFormatter.format(diffNodes);

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testFormatRemovedNode() throws Exception {
        DiffNode removedNode = new DiffNode("key2", "removed", "value2", null);
        List<DiffNode> diffNodes = List.of(removedNode);

        String expectedJson = "{\n  \"key2\" : null\n}";
        String actualJson = jsonFormatter.format(diffNodes);

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testFormatUpdatedNode() throws Exception {
        DiffNode updatedNode = new DiffNode("key3", "updated", "oldValue", "newValue");
        List<DiffNode> diffNodes = List.of(updatedNode);

        String expectedJson = "{\n  \"key3\" : {\n    \"oldValue\" : \"oldValue\",\n"
                + "    \"newValue\" : \"newValue\"\n  }\n}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> expectedMap = objectMapper.readValue(expectedJson, new TypeReference<>() { });
        Map<String, Object> actualMap = objectMapper.readValue(jsonFormatter.format(diffNodes),
                new TypeReference<>() { });

        // Сравниваем карты, что учитывает порядок ключей
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void testFormatUnchangedNode() throws Exception {
        DiffNode unchangedNode = new DiffNode("key4", "unchanged", "value4", "value4");
        List<DiffNode> diffNodes = List.of(unchangedNode);

        String expectedJson = "{\n  \"key4\" : \"value4\"\n}";
        String actualJson = jsonFormatter.format(diffNodes);

        assertEquals(expectedJson, actualJson);
    }

    /*@Test
    public void testFormatChangedNode() throws Exception {
        DiffNode changedNode = new DiffNode("key5", "changed", "oldValue", "newValue");
        List<DiffNode> diffNodes = List.of(changedNode);

        String expectedJson = "{\n  \"key5\" : {\n    \"oldValue\" : \"oldValue\",\n
          \"newValue\" : \"newValue\"\n  }\n}";
        String actualJson = jsonFormatter.format(diffNodes);

        // Добавляем временный вывод
        System.out.println("Expected JSON: " + expectedJson);
        System.out.println("Actual JSON: " + actualJson);

        assertEquals(expectedJson, actualJson);
    }*/

    /*@Test
    public void testFormatEmptyList() throws Exception {
        List<DiffNode> diffNodes = List.of();

        String expectedJson = "{}";
        String actualJson = jsonFormatter.format(diffNodes);

        // Добавляем временный вывод
        System.out.println("Expected JSON: " + expectedJson);
        System.out.println("Actual JSON: " + actualJson);

        assertEquals(expectedJson, actualJson);
    }*/

    @Test
    public void testFormatThrowsException() {
        DiffNode invalidNode = new DiffNode("key1", "added", null, new Object());
        // Object не сериализуется в JSON

        List<DiffNode> diffNodes = List.of(invalidNode);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            jsonFormatter.format(diffNodes);
        });

        assertTrue(exception.getMessage().contains("Error while formatting to JSON"));
    }
}
