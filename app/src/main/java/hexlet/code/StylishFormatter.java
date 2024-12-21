package hexlet.code;

import java.util.List;

public class StylishFormatter {

    public String format(List<DiffNode> diffNodes) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (DiffNode node : diffNodes) {
            switch (node.getType()) {
                case "added":
                    result.append(String.format("  + %s: %s\n", node.getKey(), node.getNewValue()));
                    break;
                case "removed":
                    result.append(String.format("  - %s: %s\n", node.getKey(), node.getOldValue()));
                    break;
                case "changed":
                    result.append(String.format("  - %s: %s\n", node.getKey(), node.getOldValue()));
                    result.append(String.format("  + %s: %s\n", node.getKey(), node.getNewValue()));
                    break;
                case "unchanged":
                    result.append(String.format("    %s: %s\n", node.getKey(), node.getOldValue()));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown node type: " + node.getType());
            }
        }

        result.append("}");
        return result.toString();
    }
}

