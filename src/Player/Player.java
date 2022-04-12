package Player;

import Graph.Graph;
import Items.Item;
import java.util.HashMap;


public class Player {
    private String name, description;
    private HashMap<String, Item> items;
    Graph.Room currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new HashMap<>();
    }

    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    public void removeItem(String name) {
        items.remove(name);
    }

    public void displayInventory() {
        StringBuilder s = new StringBuilder("You are carrying: ");
        for (String key : items.keySet()) {
            s.append(key).append(", ");
        }

        System.out.println(items.size() == 0 ? "You don't have any items" : s.substring(0, s.length() - 2));
    }

    public Graph.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Graph.Room newRoom) {
        currentRoom = newRoom;
    }

    public boolean moveToRoom(String name) {
        if (currentRoom.getNeighbor(name) != null) {
            setCurrentRoom(currentRoom.getNeighbor(name));
            return true;
        } else {
            return false;
        }
    }

    public boolean hasItem(String name) {
        return items.containsKey(name);
    }

    public Item getItem(String name) {
        return items.get(name);
    }

}