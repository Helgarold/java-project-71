// src/test/java/hexlet/code/PlainFormatterTest.java

package hexlet.code.formatters;

import hexlet.code.DiffNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainFormatterTest {

    @Test
    public void testFormatRemoved() {
        DiffNode node = new DiffNode("age", "removed", 30, null);
        PlainFormatter formatter = new PlainFormatter();
        String result = formatter.format(Arrays.asList(node));

        assertEquals("Property 'age' was removed", result);
    }

    @Test
    public void testFormatAdded() {
        DiffNode node = new DiffNode("city", "added", null, "New York");
        PlainFormatter formatter = new PlainFormatter();
        String result = formatter.format(Arrays.asList(node));

        assertEquals("Property 'city' was added with value: 'New York'", result);
    }

    @Test
    public void testFormatUnchanged() {
        DiffNode node = new DiffNode("country", "unchanged", "USA", "USA");
        PlainFormatter formatter = new PlainFormatter();
        String result = formatter.format(Arrays.asList(node));

        assertEquals("", result); // Ничего не должно быть возвращено для unchanged
    }

    @Test
    public void testFormatNullValue() {
        DiffNode node = new DiffNode("likes", "added", null, "coding");
        PlainFormatter formatter = new PlainFormatter();
        String result = formatter.format(Arrays.asList(node));

        assertEquals("Property 'likes' was added with value: 'coding'", result);
    }
}
