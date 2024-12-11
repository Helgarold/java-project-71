package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DifferTest {

    @Test
    void testCompareIdenticalJson() {
        String json1 = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        String json2 = "{\"key1\":\"value1\",\"key2\":\"value2\"}";

        assertTrue(Differ.compare(json1, json2), "JSON files should be equal");
    }

    @Test
    void testCompareDifferentJson() {
        String json1 = "{\"key1\":\"value1\"}";
        String json2 = "{\"key2\":\"value2\"}";

        assertFalse(Differ.compare(json1, json2), "JSON files should not be equal");
    }

    @Test
    void testCompareJsonWithDifferentValues() {
        String json1 = "{\"key1\":\"value1\"}";
        String json2 = "{\"key1\":\"value2\"}";

        assertFalse(Differ.compare(json1, json2), "JSON files should not be equal");
    }

    @Test
    void testCompareJsonWithDifferentKeys() {
        String json1 = "{\"key1\":\"value1\"}";
        String json2 = "{\"key2\":\"value1\"}";

        assertFalse(Differ.compare(json1, json2), "JSON files should not be equal");
    }
}
