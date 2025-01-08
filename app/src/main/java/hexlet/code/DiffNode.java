package hexlet.code;

import java.util.Objects;

public class DiffNode {
    private final String key;
    private final String type;
    private final Object oldValue;
    private final Object newValue;

    public DiffNode(String key, String type, Object oldValue, Object newValue) {
        this.key = key;
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getFormattedValues() {
        if (type.equals("added")) {
            return String.format("Value: %s", newValue);
        } else if (type.equals("removed")) {
            return String.format("Value: %s", oldValue);
        } else if (type.equals("updated")) {
            return String.format("From: %s to: %s", oldValue, newValue);
        }
        return "No change";
    }

    @Override
    public String toString() {
        return "DiffNode{"
                +
                "key='" + key + '\''
                +
                ", type='" + type + '\''
                +
                ", oldValue=" + oldValue
                +
                ", newValue=" + newValue
                +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DiffNode)) {
            return false;
        }
        DiffNode diffNode = (DiffNode) o;
        return key.equals(diffNode.key)
                &&
                type.equals(diffNode.type)
                &&
                Objects.equals(oldValue, diffNode.oldValue)
                &&
                Objects.equals(newValue, diffNode.newValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, type, oldValue, newValue);
    }
}

