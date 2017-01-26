package online.pizzacrust.inheritance;

/**
 * Represents a field node or variable as a child to {@link RootNode}.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface FieldNode extends SubNode {

    /**
     * Creates a new field node.
     * @param name
     * @param desc
     * @param access
     * @return
     */
    static FieldNode createFieldNode(String name, String desc, int access) {
        return new DataFieldNode(name, desc, access);
    }

}

/**
 * Represents a implementation of {@link FieldNode} using {@link BasicComparableSubNode}.
 * Used for instance checking.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
class DataFieldNode extends SubNode.BasicComparableSubNode implements FieldNode {
    protected DataFieldNode(String name, String desc, int access) {
        super(name, desc, access);
    }
}