package Entities;
import Graph.Graph;

public interface Entity {
    public String getName();
    public String getDescription();
    public void move();
    public void act();
    public Graph.Room getRoom();
}