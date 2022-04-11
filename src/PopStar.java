public class PopStar extends GenericEntity {
    public PopStar(String name, String description) {
        super(name, description);
    }

    @Override
    public void move() {
        Graph.Room curr = getCurrentRoom();
        if (curr.getPlayerRoom() != null) setCurrentRoom(curr.getPlayerRoom());

    }

    @Override
    public void act() {

    }
}
