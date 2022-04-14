package Entities;
import Graph.Graph;

public class Wumpus extends GenericEntity{
    public Wumpus(Graph.Room currentRoom) {
        super("wumpus", "a wumpus", currentRoom);
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