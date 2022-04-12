package Entities;
import Graph.Graph;

public class Chicken extends GenericEntity {
    public Chicken(String name, String description) {
        super(name, description);
    }

    @Override
    public void move() {
        Graph.Room curr = getCurrentRoom();

        if (curr.getRandomRoom() != null){
            curr.removeEntity(this);
            setCurrentRoom(curr.getRandomRoom());
            curr.addEntity(this);
        }
    }

    @Override
    public void act() {

    }
}
