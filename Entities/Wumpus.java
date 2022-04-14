package Entities;
import Graph.Graph;

public class Wumpus extends GenericEntity{
    public Wumpus(String name, String description, Graph.Room currentRoom) {
        super(name, description, currentRoom);
        currentRoom.addEntity(this);
    }

    @Override
    public void move() {
        boolean neighborsHasPlayer = this.currentRoom.neighborsHasPlayer();
        if (!neighborsHasPlayer) this.currentRoom.getRandomRoom();
        else this.currentRoom = this.currentRoom.getRandomRoomWithoutPlayer();
    }

    @Override
    public void act() {

    }
}