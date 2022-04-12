package Entities;
import Graph.Graph;

public class PopStar extends GenericEntity {
    public PopStar(String name, String description) {
        super(name, description);
    }

    @Override
    public void move() {
        Graph.Room curr = getCurrentRoom();
        if (curr.getPlayerRoom() != null)  {
            curr.removeEntity(this);
            setCurrentRoom(curr.getPlayerRoom());
            curr.addEntity(this);
        }

    }

    @Override
    public void act() {

    }
}