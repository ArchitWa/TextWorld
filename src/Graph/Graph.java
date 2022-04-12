package Graph;

import Entities.Chicken;
import Entities.Entity;
import Items.Item;

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
        private boolean hasPlayerLoc;
        private HashMap<String, Room> neighbors;
        private HashMap<String, Item> items;
        private HashMap<String, Entity> entities;

        private Room(String name) {
            neighbors = new HashMap<>();
            items = new HashMap<>();
            entities = new HashMap<>();
            this.name = name;
        }


        public void addNeighbor(Room n) {
            neighbors.put(n.name, n);
        }

        public Room getRandomRoom() {
            Object[] neighborNames = neighbors.keySet().toArray();
            //noinspection SuspiciousMethodCalls
            return neighbors.size() == 0 ? null : neighbors.get(neighbors.keySet().toArray()[(int) (Math.random() * neighbors.size())]);
        }


        public void setHasPlayerLoc() {
            hasPlayerLoc = !hasPlayerLoc;
        }

        public boolean getHasPlayerLoc() {
            return hasPlayerLoc;
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
                s.append(key).append(", ");
            }
            return items.size() == 0 ? "There are no items in this room" : s.substring(0, s.length() - 2);
        }

        public String displayEntities() {
            StringBuilder s = new StringBuilder("This room has: ");
            for (String key : entities.keySet()) {
                s.append(key).append(", ");
            }
            return entities.size() == 0 ? "There are no entities in this room" : s.substring(0, s.length() - 2);
        }

        public void addEntity(Entity entity) {
            entities.put(entity.getName(), entity);
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

        public Room getPlayerRoom() {
            for (String key : neighbors.keySet()) {
                if (neighbors.get(key).getHasPlayerLoc()) {
                    return neighbors.get(key);
                }
            }
            return null;
        }

        public boolean neighborsHasPlayer() {
            for (String key : neighbors.keySet()) {
                if (neighbors.get(key).getHasPlayerLoc()) {
                    return true;
                }
            }
            return false;
        }

        public void removeEntity(Entity entity) {
            entities.remove(entity.getName());
        }
    }

}