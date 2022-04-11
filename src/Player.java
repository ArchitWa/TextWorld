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
        System.out.println("You are carrying: ");
        for (String key : items.keySet()) {
            System.out.print(key + " ");
        }
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
            System.out.println("You moved to: " + name);
            return true;
        } else {
            System.out.println("You cannot go there!");
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
