package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DifferTest {

    @Test
    void testCompareDifferentJson() throws IOException {
        List<DiffNode> diffNodes = Differ.generate("src/test/resources/file1.json",
                "src/test/resources/file2.json");
        assertFalse(diffNodes.isEmpty(), "JSON files should not be equal");
    }

    @Test
    void testCompareJsonWithDifferentValues() throws IOException {
        List<DiffNode> diffNodes = Differ.generate("src/test/resources/filepath1.json",
                "src/test/resources/filepath2.json");
        assertFalse(diffNodes.isEmpty(), "JSON files should not be equal");
    }

    @Test
    void testCompareJsonWithDifferentKeys() throws IOException {
        List<DiffNode> diffNodes = Differ.generate("src/test/resources/file1.json",
                "src/test/resources/file2.json");
        assertFalse(diffNodes.isEmpty(), "JSON files should not be equal");
    }
}
