import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private List<Room> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public void addNode(String name) {
        nodes.add(new Room(name));
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getNode(name1);
        Room n2 = getNode(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getNode(name1);
        Room n2 = getNode(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public Room getNode(String name) {
        for (Room n : nodes) if (n.getName().equals(name)) return n;
        return null;
    }

    public static class Room {
        private String name;
        private HashMap<String ,Room> neighbors;
        private HashMap<String, Item> items;

        private Room(String name) {
            neighbors = new HashMap<>();
            items = new HashMap<>();
            this.name = name;
        }

        public void addNeighbor(Room n) {
            neighbors.put(n.name, n);
        }

        /**
         * Adds a neighbor to the current node AND adds the current node as a neighbor of n
         *
         * @param n
         */
        private void addTwoDirectionNeighbor(Room n) {
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
            for (String key : neighbors.keySet()) {
                s.append(key).append(" ");
            }
            return s.toString();
        }

        /**
         * Return neighbor whose name is name. Returns null otherwise
         *
         * @param nName name of neighboring node to return
         * @return returns neighboring node with correct name
         */
        public Room getNeighbor(String nName) {
            return neighbors.getOrDefault(nName, null);
        }

        public String displayItems() {
           StringBuilder s = new StringBuilder("This room has: ");
            for (String key : items.keySet()) {
                s.append(key).append(" ");
            }
            return items.size() == 0 ? "There are no items in this room" : s.toString();
        }

        public void addItem(String name) {
            items.put(name, new Item(name, ""));
        }

        public void addItem(String name, String description) {
            items.put(name, new Item(name, description));
        }

        public void addItem(Item item) {
            items.put(item.getName(), item);
        }

        public Item removeItem(String name) {
            Item item = items.get(name);
            items.remove(name);
            return item;
        }

        public boolean hasItem(String name) {
            return items.containsKey(name);
        }

        public Item getItem(String name) {
            return items.get(name);
        }

        public String getName() {
            return name;
        }
    }

}



