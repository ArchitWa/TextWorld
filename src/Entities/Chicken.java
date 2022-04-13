package Entities;
import Graph.Graph;

public class Chicken extends GenericEntity {
    public Chicken(String name, String description, Graph.Room currentRoom) {
        super(name, description, currentRoom);
        this.currentRoom.addEntity(this);
    }

    @Override
    public void move() {
        Graph.Room next = this.currentRoom.getRandomRoom();
        if (next == null) return;;

        this.currentRoom.removeEntity(this);
        this.currentRoom = next;
        this.currentRoom.addEntity(this);
    }

    @Override
    public void act() {

    }
}