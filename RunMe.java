package Main;

import Commands.*;
import Graph.Graph;
import Player.Player;

import java.util.Scanner;

public class RunMe {
    public static void main(String[] args) {

        /* Creating the graph and player */
        Player player = new Player("Bob", "An adventurous fellow");
        Graph graph = new Graph(player);
        CommandParser parser = new CommandParser();

        initRooms(graph);
        initItems(graph);
        initEntities(graph);

        player.setCurrentRoom(graph.getNode("hall"));
        Graph.Room currentRoom;

        initCommands(parser, player, graph);

        String response = "";
        Scanner s = new Scanner(System.in);

        /* Display all the commands they can type, so they know what to do */
        parser.displayCommands();
        System.out.println("--------------------------");

        do {
            currentRoom = player.getCurrentRoom();
            currentRoom.setHasPlayer();
            System.out.println("You are currently in the " + currentRoom.getName());
            System.out.print("What do you want to do? > ");

            response = s.nextLine().trim();

            Command nextCmd = parser.parseCommandString(response);

            System.out.println("--------------------------");
            if (nextCmd != null) nextCmd.execute();
            else parser.displayCommands();
            System.out.println("--------------------------");

            graph.moveAllEntities();
        } while (!response.equals("quit"));
    }

    private static void initCommands(CommandParser parser, Player player, Graph graph) {
        parser.addCommand(new GoCommand(player));
        parser.addCommand(new LookCommand(graph));
        parser.addCommand(new RoomsCommand(player));
        parser.addCommand(new AddRoomCommand(graph));
        parser.addCommand(new TakeCommand(player));
        parser.addCommand(new DropCommand(player));
    }

    private static void initItems(Graph g) {
        g.createItem("paper", "hall");
        g.createItem("pen", "hall");
        g.createItem("key", "dungeon");
        g.createItem("toothbrush", "bathroom");
    }

    private static void initEntities(Graph g) {
        g.createRandomChickens(10);
        g.createWumpus("dungeon");
        g.createPopStar("bathroom");
    }

    private static void initRooms(Graph g) {
        g.addNode("hall");
        g.addNode("closet");
        g.addNode("dungeon");
        g.addNode("bathroom");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("closet", "bathroom");
    }
}