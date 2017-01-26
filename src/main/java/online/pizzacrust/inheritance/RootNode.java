package online.pizzacrust.inheritance;

import java.util.List;
import java.util.Optional;

/**
 * Represents a root node for other {@link RootNode} as super node and
 * implementations.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface RootNode {

    /**
     * Retrieves the name of the root node.
     * @return
     */
    String getName();

    /**
     * Retrieves the super node of the current root node.
     * @return the optional
     */
    Optional<RootNode> getSuperNode();

    /**
     * Retrieves the nodes that this root node implement.
     * @return the list
     */
    List<RootNode> getImplementationNodes();

    /**
     * Retrieves methods from the root node.
     * @return
     */
    List<MethodNode> getMethods();

    interface FieldCompatible extends RootNode {

        /**
         * Retrieves fields from the root node.
         * @return
         */
        List<FieldNode> getFields();

    }

    /**
     * Creates a node as a interface.
     * @param name
     * @param superNode
     * @param interfaces
     * @param methods
     * @return
     */
    static RootNode createInterfaceNode(String name, RootNode superNode, List<RootNode> interfaces,
                                        List<MethodNode> methods) {
        return new InterfaceRootNode(name, superNode, interfaces, methods);
    }

    /**
     * Creates a node as a class.
     * @param name
     * @param superNode
     * @param interfaces
     * @param methods
     * @param fields
     * @return
     */
    static RootNode.FieldCompatible createRegularNode(String name, RootNode superNode, List<RootNode> interfaces,
                                                      List<MethodNode> methods, List<FieldNode>
                                                              fields) {
        return new RegularClassNode(name, superNode, interfaces, methods, fields);
    }

}

class InterfaceRootNode implements RootNode {

    private final String name;
    private final RootNode superNode;
    private final List<RootNode> interfaces;
    private final List<MethodNode> methods;

    protected InterfaceRootNode(String name, RootNode superNode, List<RootNode> interfaces,
                                List<MethodNode> methods) {
        this.name = name;
        this.superNode = superNode;
        this.interfaces = interfaces;
        this.methods = methods;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<RootNode> getSuperNode() {
        if (superNode != null) {
            return Optional.of(superNode);
        }
        return Optional.empty();
    }

    @Override
    public List<RootNode> getImplementationNodes() {
        return this.interfaces;
    }

    @Override
    public List<MethodNode> getMethods() {
        return this.methods;
    }

}

class RegularClassNode extends InterfaceRootNode implements RootNode.FieldCompatible {

    private final List<FieldNode> fields;

    protected RegularClassNode(String name, RootNode superNode, List<RootNode> interfaces,
                               List<MethodNode> methods, List<FieldNode> fields) {
        super(name, superNode, interfaces, methods);
        this.fields = fields;
    }

    @Override
    public List<FieldNode> getFields() {
        return this.fields;
    }
}