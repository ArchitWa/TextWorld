package Entities;
import Graph.Graph;

public abstract class GenericEntity implements Entity {
    private String name, description;
    protected Graph.Room currentRoom;

    public GenericEntity(String name, String description, Graph.Room currentRoom) {
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
    }

    @Override
    public Graph.Room getRoom() {
        return currentRoom;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public abstract void move();

    @Override
    public abstract void act();

    public Graph.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Graph.Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}