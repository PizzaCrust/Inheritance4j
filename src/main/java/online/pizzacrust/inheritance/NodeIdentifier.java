package online.pizzacrust.inheritance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Identifies nodes into {@link IdentifiedSubNode}.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class NodeIdentifier {

    private final List<RootNode> classpath;

    public NodeIdentifier(List<RootNode> classpath) {
        this.classpath = classpath;
    }

    public List<IdentifiedRootNode> translateClasspath() {
        ArrayList<IdentifiedRootNode> identifiedRootNodes = new ArrayList<>();
        classpath.forEach((classpathNode) -> identifiedRootNodes.add(this.identifyRootNode
                (classpathNode)));
        return identifiedRootNodes;
    }

    private RootNode recursiveParentFieldSearch(RootNode.FieldCompatible declaringClass, FieldNode
            fieldNode) {
        if (declaringClass.getFields().contains(fieldNode)) {
            RootNode candidate = declaringClass;
            if (declaringClass.getSuperNode().isPresent()) {
                candidate = recursiveParentFieldSearch((RootNode.FieldCompatible) declaringClass.getSuperNode().get(),
                        fieldNode);
            }
            if (candidate == null) {
                return declaringClass;
            }
            return candidate;
        }
        return null;
    }

    private RootNode recursiveParentSearch(RootNode declaringClass, MethodNode methodNode) {
        if (declaringClass.getMethods().contains(methodNode)) {
            RootNode candidate = declaringClass;
            if (declaringClass.getSuperNode().isPresent()) {
                candidate = recursiveParentSearch(declaringClass.getSuperNode().get(), methodNode);
            }
            if (candidate == null) {
                for (RootNode interfaceClass : declaringClass.getImplementationNodes()) {
                    candidate = recursiveParentSearch(interfaceClass, methodNode);
                    if (candidate != null) {
                        break;
                    }
                }
            }
            return candidate;
        }
        return null;
    }

    public static void main(String... args) {
        FieldNode oneField = FieldNode.createFieldNode("meow4Field", "meow2",3);
        MethodNode one = MethodNode.createMethodNode("meow3", "meow", 1);
        MethodNode two = MethodNode.createMethodNode("meow2", "meow1", 2);
        RootNode meow3Interface = RootNode.createInterfaceNode("meow3Class", null, new
                        ArrayList<>(),
                Collections.singletonList(one));
        RootNode meow2Super = RootNode.createRegularNode("meow2Class", null, new ArrayList<>(),
                Collections.singletonList(two), Collections.singletonList(oneField));
        RootNode mainClass = RootNode.createRegularNode("mainclass", meow2Super, Collections
                .singletonList(meow3Interface), Arrays.asList(one, two), Collections.singletonList(oneField));
        RootNode parentClassOfMeow3 = new NodeIdentifier(Arrays.asList(meow3Interface,
                meow2Super, mainClass)).recursiveParentSearch(mainClass, one);
        System.out.println(parentClassOfMeow3.getName());
        RootNode parentClassOfMeow2 = new NodeIdentifier(Arrays.asList(meow3Interface,
                meow2Super, mainClass)).recursiveParentSearch(mainClass, two);
        System.out.println(parentClassOfMeow2.getName());
        System.out.println(new NodeIdentifier(Arrays.asList(meow3Interface, meow2Super,
                mainClass)).identifyMethod(mainClass, one).getAbsoluteParent().getName());
        System.out.println(new NodeIdentifier(Arrays.asList(meow3Interface, meow2Super,
                mainClass)).identifyMethod(meow3Interface, one).getAbsoluteParent().getName());
        RootNode parentClassOfOneField = new NodeIdentifier(Arrays.asList(meow3Interface,
                meow2Super, mainClass)).identifyField((RootNode.FieldCompatible) mainClass,
                oneField).getAbsoluteParent();
        System.out.println(parentClassOfOneField.getName());
    }

    private IdentifiedRootNode identifyRootNode(RootNode rootNode) {
        List<IdentifiedSubNode<FieldNode>> fields = new ArrayList<>();
        List<IdentifiedSubNode<MethodNode>> methods = new ArrayList<>();
        rootNode.getMethods().forEach((methodNode) -> methods.add(this.identifyMethod(rootNode,
                methodNode)));
        if (rootNode instanceof RootNode.FieldCompatible) {
            RootNode.FieldCompatible fieldCompatible = (RootNode.FieldCompatible) rootNode;
            fieldCompatible.getFields().forEach((fieldNode) -> fields.add(this.identifyField
                    (fieldCompatible, fieldNode)));
        }
        List<IdentifiedSubNode> combinedNodes = new ArrayList<>();
        combinedNodes.addAll(fields);
        combinedNodes.addAll(methods);
        return new IdentifiedRootNode(rootNode.getName(), combinedNodes);
    }

    private IdentifiedSubNode<FieldNode> identifyField(RootNode.FieldCompatible declaringClass, FieldNode
            fieldNode) {
        return new IdentifiedSubNode<>(fieldNode, recursiveParentFieldSearch(declaringClass,
                fieldNode));
    }

    private IdentifiedSubNode<MethodNode> identifyMethod(RootNode declaringClass, MethodNode methodNode) {
        return new IdentifiedSubNode<>(methodNode, recursiveParentSearch(declaringClass, methodNode));
    }

}