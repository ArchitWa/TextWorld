package Entities;
import Graph.Graph;

public class Wumpus extends GenericEntity{
    public Wumpus(String name, String description) {
        super(name, description);
    }

    @Override
    public void move() {
        Graph.Room curr = getCurrentRoom();

        if (curr.neighborsHasPlayer()) {
            curr.removeEntity(this);
            setCurrentRoom(curr.getRandomRoom());
            curr.addEntity(this);
        }
    }

    @Override
    public void act() {

    }
}