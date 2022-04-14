package Commands;

import Graph.Graph;

public class LookCommand implements Command {
    private Graph graph;
    private Graph.Room currentRoom;

    public LookCommand(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void init(String response) {
        this.currentRoom = graph.getPlayerRoom();
    }

    @Override
    public boolean execute() {
        System.out.println(graph.getPlayerRoom().displayItems());
        System.out.println(graph.getPlayerRoom().displayEntities());
        return true;
    }

    @Override
    public String getCommandWord() {
        return "look";
    }
}
