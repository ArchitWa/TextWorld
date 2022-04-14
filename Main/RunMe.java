package Main;

import Commands.*;
import Entities.*;
import Graph.Graph;
import Player.Player;

import java.util.Scanner;

public class RunMe {
    public static void main(String[] args) {

        /* Creating the graph and player */
        Player player = new Player("Bob", "An adventurous fellow");
        Graph graph = new Graph(player);
        player.setCurrentRoom(graph.getNode("hall"));
        Graph.Room currentRoom;


        CommandParser parser = new CommandParser();

        initRooms(graph);
        initItems(graph);
        initEntities(graph);
        
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

//            String[] arr = response.split(" ");
//            if (arr[0].equals("go")) {
//                go(player, arr);
//                currentRoom.setHasPlayer();
//            } else if (arr[0].equals("look")) {
//                System.out.println(currentRoom.displayItems());
//                System.out.println(currentRoom.displayEntities());
//            } else if (arr[0].equals("rooms")) {
//                rooms(currentRoom);
//            } else if (response.startsWith("add room")) {
//                addRoom(arr, graph, player.getCurrentRoom());
//            } else if (arr[0].equals("take")) {
//                take(currentRoom, arr, player);
//            } else if (arr[0].equals("drop")) {
//                drop(currentRoom, arr, player);
//            } else if (arr[0].equals("quit")) {
//                System.out.println("You have ended the game.");
//            } else {
//                System.out.println("You can use the following commands: \ngo [room name]\nlook\nadd room [directed/undirected] [room name]\nquit\ntake [item]\ndrop [item]\nrooms");
//            }
            System.out.println("--------------------------");

            graph.moveAllEntities();


        } while (!response.equals("quit"));
    }

    private static void initCommands(CommandParser parser, Player player, Graph graph) {
        parser.addCommand(new GoCommand(player));
        parser.addCommand(new LookCommand(graph));
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

    private static void go(Player player, String[] arr) {
        if (player.moveToRoom(arr[1])) {
            System.out.println("You moved to: " + arr[1]);
        } else {
            System.out.println("You cannot go there!");
        }
    }

    private static void drop(Graph.Room currentRoom, String[] arr, Player player) {
        if (player.hasItem(arr[1])) {
            currentRoom.addItem(player.getItem(arr[1]));
            player.removeItem(arr[1]);
        } else {
            System.out.println("You do not have this item!");
        }
    }

    private static void take(Graph.Room currentRoom, String[] arr, Player player) {
        if (currentRoom.hasItem(arr[1])) {
            player.addItem(currentRoom.getItem(arr[1]));
            currentRoom.removeItem(arr[1]);
            System.out.println("You have grabbed the " + arr[1] + " item");
        } else {
            System.out.println("This room does not have this item!");
        }
    }

    private static void rooms(Graph.Room currentRoom) {
        System.out.println(
                currentRoom.getNeighborNames().equals("") ?
                        "You cannot go anywhere" : "You can go to the: " + currentRoom.getNeighborNames());
    }

    private static void addRoom(String[] arr, Graph g, Graph.Room current) {
        if (arr.length < 4)
            System.out.println("You must specify what you would like the room to be called and its direction");
        else {
            g.addNode(arr[3]);
            if (arr[2].equals("directed")) {
                System.out.println("A new directed room was added: " + arr[3]);
                g.addDirectedEdge(current.getName(), arr[3]);
            } else if (arr[2].equals("undirected")) {
                System.out.println("A new undirected room was added: " + arr[3]);
                g.addUndirectedEdge(current.getName(), arr[3]);
            } else System.out.println("You did not specify the direction!");
        }
    }
}