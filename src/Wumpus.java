import java.util.HashMap;

public class Wumpus extends GenericEntity{
    public Wumpus(String name, String description) {
        super(name, description);
    }

    @Override
    public void move() {
        Graph.Room curr = getCurrentRoom();

        if (curr.neighborsHasPlayer()) {
            setCurrentRoom(curr.getRandomRoom());
        }
    }

    @Override
    public void act() {

    }
}
