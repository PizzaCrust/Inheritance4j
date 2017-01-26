package online.pizzacrust.inheritance;

/**
 * Represents a sub node to {@link RootNode}.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface SubNode extends Comparable<SubNode> {

    /**
     * Retrieves the name of the node.
     * @return
     */
    String getName();

    /**
     * Retrieves the description of the node.
     * @return
     */
    String getDesc();

    /**
     * Retrieves the access of the node.
     * @return
     */
    int getAccess();

    /**
     * Creates a data node according to the specified data set.
     * Do not use this! Use individual implementations, so API users can identify which type is
     * which type.
     * @param name
     * @param desc
     * @param access
     * @return
     */
    @Deprecated
    static SubNode createDataNode(String name, String desc, int access) {
        return new BasicComparableSubNode(name, desc, access);
    }

    /**
     * Represents a basic comparable sub node, should be the default implementation of SubNode.
     *
     * @since 1.0-SNAPSHOT
     * @author PizzaCrust
     */
    class BasicComparableSubNode implements SubNode {

        private final String name;
        private final String desc;
        private final int access;

        protected BasicComparableSubNode(String name, String desc, int access) {
            this.name = name;
            this.desc = desc;
            this.access = access;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }

        @Override
        public int getAccess() {
            return this.access;
        }

        @Override
        public int compareTo(SubNode o) {
            return name.compareTo(o.getName()) + getDesc().compareTo(desc) + ((Integer) getAccess()
            ).compareTo(o.getAccess());
        }

        @Override
        public boolean equals(Object object) {
            if (!getClass().isAssignableFrom(object.getClass())) {
                return false;
            }
            SubNode subNode = (SubNode) object;
            return subNode.getAccess() == this.access && subNode.getDesc().equals(this.desc) &&
                    subNode.getName().equals(this.name);
        }

    }

}