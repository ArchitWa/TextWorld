package Graph;

import Entities.*;
import Entities.Wumpus;
import Items.Item;
import Player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private HashMap<String, Room> nodes;
    private HashMap<String, GenericEntity> entityList;
    private Player player;

    public Graph(Player player) {
        nodes = new HashMap<>();
        entityList = new HashMap<>();
        this.player = player;
    }

    public void addNode(String name) {
        nodes.put(name, new Room(name));
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
        return nodes.get(name);
    }

    public void moveAllEntities() {
        for (String key : entityList.keySet()) {
            GenericEntity e = entityList.get(key);
            e.move();
        }
    }

    private HashMap<String, GenericEntity> getEntityList() {
        return entityList;
    }

    public void createItem(String itemName, String roomName) {
        Room room = nodes.get(roomName);
        room.addItem(itemName);
    }

    public void createRandomChickens(int n) {
        for (int i = 0; i < n; i++) {
            Object[] roomNames = nodes.keySet().toArray();
            @SuppressWarnings("SuspiciousMethodCalls")
            Chicken c = (Chicken) (new Chicken(nodes.get((String) roomNames[(int) (Math.random() * nodes.size())]))).setName("Chicken " + i);
        }
    }

    public void createWumpus(String roomName) {
        Wumpus w = (Wumpus) new Wumpus(nodes.get(roomName)).setName("Wumpus " + (int)( Math.random() * 100));
    }

    public void createPopStar(String bathroom) {
        PopStar p = (PopStar) new PopStar(nodes.get("bathroom")).setName("Popstar " + (int)(Math.random() * 100));
    }

    public Room getPlayerRoom() {
        return player.getCurrentRoom();
    }

    public class Room {
        private String name;
        private boolean hasPlayer;
        private HashMap<String, Room> neighbors;
        private HashMap<String, Item> items;

        private Room(String name) {
            neighbors = new HashMap<>();
            items = new HashMap<>();
            this.name = name;
        }


        public void addNeighbor(Room n) {
            neighbors.put(n.name, n);
        }


        public void setHasPlayer() {
            hasPlayer = !hasPlayer;
        }

        public boolean getHasPlayer() {
            return hasPlayer;
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
            HashMap<String, GenericEntity> entityList = getEntityList();

            for (String key : entityList.keySet()) {
                if (entityList.get(key).getCurrentRoom().getName().equals(this.name))
                    s.append(entityList.get(key).getName()).append(", ");
            }
            return s.toString().equals("This room has: ") ? "There are no entities in this room" : s.substring(0, s.length() - 2);
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
            if (hasPlayer) return this;
            for (String key : neighbors.keySet()) {
                if (neighbors.get(key).getHasPlayer()) {
                    return neighbors.get(key);
                }
            }
            return null;
        }

        public boolean neighborsHasPlayer() {
            for (String key : neighbors.keySet()) {
                if (neighbors.get(key).getHasPlayer()) {
                    return true;
                }
            }
            return false;
        }

        public Room getRandomRoom() {
            //noinspection SuspiciousMethodCalls
            return neighbors.size() == 0 ? null : neighbors.get(neighbors.keySet().toArray()[(int) (Math.random() * neighbors.size())]);
        }

        public Graph.Room getRandomRoomWithoutPlayer() {
            ArrayList<Room> temp = new ArrayList<>();
            for (String key : neighbors.keySet()) if (!neighbors.get(key).hasPlayer) temp.add(neighbors.get(key));
            return temp.get((int) (Math.random() * temp.size()));
        }

        public void addEntity(GenericEntity entity) {
            getEntityList().put(entity.getName(), entity);
        }

        public void removeEntity(GenericEntity entity) {
            getEntityList().remove(entity.getName());
        }
    }
}