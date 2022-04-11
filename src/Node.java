import java.util.ArrayList;

public class Node {
    private String name;
    private ArrayList<Node> neighbors;

    public Node(String name) {
        neighbors = new ArrayList<>();
        this.name = name;
    }

    public void addNeighbor(Node n) {
        neighbors.add(n);
    }

    /**
     * Adds a neighbor to the current node AND adds the current node as a neighbor of n
     * @param n
     */
    public void addTwoDirectionNeighbor(Node n) {
        addNeighbor(n);
        n.addNeighbor(this);
    }

    /**
     * Returns a String of the names of all the neighbors of this node.
     *
     * @return
     */
    public String getNeighborNames() {
        StringBuilder s = new StringBuilder();
        for (Node n : neighbors) s.append(n.getName()).append(" ");
        return s.toString();
    }

    /**
     * Return neighbor whose name is name. Returns null otherwise
     *
     * @param nName name of neighboring node to return
     * @return returns neighboring node with correct name
     */
    public Node getNeighbor(String nName) {
        for (Node n : neighbors) if (n.getName().equals(nName)) return n;
        return null;
    }

    public String getName() {
        return name;
    }

}
