public class Chicken extends GenericEntity {
    public Chicken(String name, String description) {
        super(name, description);
    }

    @Override
    public void move() {
        Graph.Room curr = getCurrentRoom();
        setCurrentRoom(curr.getRandomRoom());
    }

    @Override
    public void act() {

    }
}
