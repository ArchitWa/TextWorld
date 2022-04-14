package Entities;

import Graph.Graph;

public class PopStar extends GenericEntity {
    public PopStar(Graph.Room currentRoom) {
        super("popstar", "a popstar", currentRoom);
        this.currentRoom.addEntity(this);
    }

    @Override
    public void move() {
        Graph.Room next = this.currentRoom.getPlayerRoom();
        if (next == null) return;

        this.currentRoom = next;
    }

    @Override
    public void act() {

    }
}