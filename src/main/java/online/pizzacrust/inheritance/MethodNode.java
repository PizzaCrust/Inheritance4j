package online.pizzacrust.inheritance;

/**
 * Represents a method node or function as a child to {@link RootNode}.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface MethodNode extends SubNode {

    /**
     * Creates a new method node.
     * @param name
     * @param desc
     * @param access
     * @return
     */
    static MethodNode createMethodNode(String name, String desc, int access) {
        return new DataMethodNode(name, desc, access);
    }

}

/**
 * Implementation of {@link SubNode} using {@link BasicComparableSubNode}.
 * A different class is created to allow instance checking of nodes.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
class DataMethodNode extends SubNode.BasicComparableSubNode implements MethodNode {

    protected DataMethodNode(String name, String desc, int access) {
        super(name, desc, access);
    }

}