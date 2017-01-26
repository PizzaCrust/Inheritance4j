package online.pizzacrust.inheritance;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a identified {@link RootNode}.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class IdentifiedRootNode {

    private final List<IdentifiedSubNode> subNodes;

    public IdentifiedRootNode(List<IdentifiedSubNode> nodes) {
        this.subNodes = nodes;
    }

    public List<IdentifiedSubNode> getSubNodes() {
        return subNodes;
    }

    public List<IdentifiedSubNode> findSubnodes(SubNodeType type) {
        ArrayList<IdentifiedSubNode> identifiedSubNodes = new ArrayList<>();
        if (type == SubNodeType.FIELD) {
            getSubNodes().forEach((subNode) -> {
                if (subNode.getSubnode() instanceof FieldNode) {
                    identifiedSubNodes.add(subNode);
                }
            });
        } else if (type == SubNodeType.METHOD) {
            getSubNodes().forEach((subNode) -> {
                if (subNode.getSubnode() instanceof MethodNode) {
                    identifiedSubNodes.add(subNode);
                }
            });
        }
        return identifiedSubNodes;
    }


}
