package Commands;

import Graph.Graph;

public class AddRoomCommand implements Command {
    private Graph graph;
    private Graph.Room currentRoom;
    private String[] arr;

    public AddRoomCommand(Graph graph) {
        this.graph = graph;
        this.currentRoom = graph.getPlayerRoom();
    }

    @Override
    public void init(String response) {
        this.arr = response.split(" ");
    }

    @Override
    public boolean execute() {
        if (arr.length < 3) {
            System.out.println("You must specify what you would like the room to be called and its direction");
            return false;
        } else {
            graph.addNode(arr[2]);
            if (arr[1].equals("directed")) {
                System.out.println("A new directed room was added: " + arr[2]);
                graph.addDirectedEdge(currentRoom.getName(), arr[2]);
                return true;
            } else if (arr[1].equals("undirected")) {
                System.out.println("A new undirected room was added: " + arr[2]);
                graph.addUndirectedEdge(currentRoom.getName(), arr[2]);
                return true;
            } else {
                System.out.println("You did not specify the direction!");
                return false;
            }
        }
    }

    @Override
    public String getCommandWord() {
        return "add-room";
    }
}
