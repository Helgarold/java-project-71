package hexlet.code.formatters;

import hexlet.code.DiffNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishFormatterTest {

    @Test
    public void testFormatAddedNode() {
        DiffNode node = new DiffNode("key1", "added", null, "value1");
        List<DiffNode> nodes = Arrays.asList(node);

        StylishFormatter stylishFormatter = new StylishFormatter();
        String result = stylishFormatter.format(nodes);

        String expected = "{\n"
                +
                "  + key1: 'value1'\n"
                +
                "}";
        assertEquals(expected, result);
    }

    @Test
    public void testFormatRemovedNode() {
        DiffNode node = new DiffNode("key2", "removed", "value2", null);
        List<DiffNode> nodes = Arrays.asList(node);

        StylishFormatter stylishFormatter = new StylishFormatter();
        String result = stylishFormatter.format(nodes);

        String expected = "{\n"
                +
                "  - key2: 'value2'\n"
                +
                "}";
        assertEquals(expected, result);
    }

    @Test
    public void testFormatUnchangedNode() {
        DiffNode node = new DiffNode("key4", "unchanged", "unchangedValue", null);
        List<DiffNode> nodes = Arrays.asList(node);

        StylishFormatter stylishFormatter = new StylishFormatter();
        String result = stylishFormatter.format(nodes);

        String expected = "{\n"
                +
                "    key4: 'unchangedValue'\n"
                +
                "}";
        assertEquals(expected, result);
    }

    @Test
    public void testFormatMultipleNodes() {
        DiffNode node1 = new DiffNode("key1", "added", null, "value1");
        DiffNode node2 = new DiffNode("key2", "removed", "value2", null);
        DiffNode node3 = new DiffNode("key3", "changed", "oldValue", "newValue");
        DiffNode node4 = new DiffNode("key4", "unchanged", "unchangedValue", null);

        List<DiffNode> nodes = Arrays.asList(node1, node2, node3, node4);

        StylishFormatter stylishFormatter = new StylishFormatter();
        String result = stylishFormatter.format(nodes);

        String expected = "{\n"
                +
                "  + key1: 'value1'\n"
                +
                "  - key2: 'value2'\n"
                +
                "  - key3: 'oldValue'\n"
                +
                "  + key3: 'newValue'\n"
                +
                "    key4: 'unchangedValue'\n"
                +
                "}";
        assertEquals(expected, result);
    }
}
