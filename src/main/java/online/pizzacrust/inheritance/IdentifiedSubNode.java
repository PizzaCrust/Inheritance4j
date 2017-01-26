package online.pizzacrust.inheritance;

/**
 * Represents a identified sub node.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class IdentifiedSubNode<T extends SubNode> {

    private final T subnode;
    private final RootNode absoluteParent;

    public IdentifiedSubNode(T subnode, RootNode absoluteParent) {
        this.subnode = subnode;
        this.absoluteParent = absoluteParent;
    }

    public RootNode getAbsoluteParent() {
        return absoluteParent;
    }

    public T getSubnode() {
        return subnode;
    }


}