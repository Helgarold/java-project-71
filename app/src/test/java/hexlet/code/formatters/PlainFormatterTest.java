/*package hexlet.code.formatters;

import hexlet.code.DiffNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainFormatterTest {

    @Test
    public void testFormatWithAddedNode() {
        List<DiffNode> nodes = new ArrayList<>();
        nodes.add(new DiffNode("newKey", "added", null, "newValue"));

        PlainFormatter formatter = new PlainFormatter();
        String expected = "{\n+ newKey: newValue\n}";
        String result = formatter.format(nodes);

        assertEquals(expected, result);
    }

    @Test
    public void testFormatWithRemovedNode() {
        List<DiffNode> nodes = new ArrayList<>();
        nodes.add(new DiffNode("removedKey", "removed", "oldValue", null));

        PlainFormatter formatter = new PlainFormatter();
        String expected = "{\n- removedKey: oldValue\n}";
        String result = formatter.format(nodes);

        assertEquals(expected, result);
    }

    @Test
    public void testFormatWithUpdatedNode() {
        List<DiffNode> nodes = new ArrayList<>();
        nodes.add(new DiffNode("updatedKey", "updated", "oldValue", "newValue"));

        PlainFormatter formatter = new PlainFormatter();
        String expected = "{\n- updatedKey: oldValue\n+ updatedKey: newValue\n}";
        String result = formatter.format(nodes);

        assertEquals(expected, result);
    }

    @Test
    public void testFormatWithMultipleNodes() {
        List<DiffNode> nodes = new ArrayList<>();
        nodes.add(new DiffNode("key1", "added", null, "value1"));
        nodes.add(new DiffNode("key2", "removed", "value2", null));
        nodes.add(new DiffNode("key3", "updated", "oldValue", "newValue"));

        PlainFormatter formatter = new PlainFormatter();
        String expected = "{\n+ key1: value1\n- key2: value2\n- key3: oldValue\n+ key3: newValue\n}";
        String result = formatter.format(nodes);

        assertEquals(expected, result);
    }

    @Test
    public void testFormatWithUnchangedNode() {
        List<DiffNode> nodes = new ArrayList<>();
        nodes.add(new DiffNode("key", "unchanged", "value", "value"));

        PlainFormatter formatter = new PlainFormatter();
        String expected = "{\n\n}";
        String result = formatter.format(nodes);

        assertEquals(expected, result);
    }
} */
