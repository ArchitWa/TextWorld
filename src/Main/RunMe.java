package Main;

import Entities.*;
import Entities.GenericEntity;
import Graph.Graph;
import Player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class RunMe {
    public static void main(String[] args) {

        /* Creating the graph */
        Graph g = createGraph();

        /* Creating the player and adding him to the graph */
        Player player = new Player("Bob", "An adventurous fellow");
        player.setCurrentRoom(g.getNode("hall"));
        Graph.Room currentRoom;

        ArrayList<GenericEntity> entityList = createEntities(g);

        String response = "";
        Scanner s = new Scanner(System.in);

        /* Display all the commands they can type, so they know what to do */
        System.out.println("You can use the following commands: \ngo [room name]\nlook\nadd room [directed/undirected] [room name]\nquit\ntake [item]\ndrop [item]\nrooms");
        System.out.println("--------------------------");
        do {
            currentRoom = player.getCurrentRoom();
            currentRoom.setHasPlayerLoc();
            System.out.println("You are currently in the " + currentRoom.getName());
            System.out.print("What do you want to do? > ");

            response = s.nextLine();
            String[] arr = response.split(" ");
            System.out.println("--------------------------");

            if (arr[0].equals("go")) {
                go(player, arr);
                currentRoom.setHasPlayerLoc();
            } else if (arr[0].equals("look")) {
                System.out.println(currentRoom.displayItems());
                System.out.println(currentRoom.displayEntities());
            } else if (arr[0].equals("rooms")) {
                rooms(currentRoom);
            }else if (response.startsWith("add room")) {
                addRoom(arr, g, player.getCurrentRoom());
            } else if (arr[0].equals("take")) {
                take(currentRoom, arr, player);
            } else if (arr[0].equals("drop")) {
                drop(currentRoom, arr, player);
            } else if (arr[0].equals("quit")) {
                System.out.println("You have ended the game.");
            } else {
                System.out.println("You can use the following commands: \ngo [room name]\nlook\nadd room [directed/undirected] [room name]\nquit\ntake [item]\ndrop [item]\nrooms");
            }
            System.out.println("--------------------------");

            for (Entity e : entityList) e.move();
        } while (!response.equals("quit"));
    }

    private static ArrayList<GenericEntity> createEntities(Graph g) {
        ArrayList<GenericEntity> entities = new ArrayList<>();

        Chicken c1 = new Chicken("Chicken 1", "A slow chicken");
        c1.setCurrentRoom(g.getNode("closet"));
        g.getNode("closet").addEntity(c1);
        entities.add(c1);

        Chicken c2 = new Chicken("Chicken 2", "A slower chicken");
        c2.setCurrentRoom(g.getNode("closet"));
        g.getNode("closet").addEntity(c2);
        entities.add(c2);

        Wumpus w1 = new Wumpus("Wumpus 1", "What even is this");
        w1.setCurrentRoom(g.getNode("dungeon"));
        g.getNode("dungeon").addEntity(w1);
        entities.add(w1);

        PopStar p1 = new PopStar("Popstar 1", "Ok");
        p1.setCurrentRoom(g.getNode("bathroom"));
        g.getNode("bathroom").addEntity(p1);
        entities.add(p1);

        return entities;
    }

    private static Graph createGraph() {
        Graph g = new Graph();
        g.addNode("hall");
        g.getNode("hall").addItem("paper");
        g.getNode("hall").addItem("pen");

        g.addNode("closet");
        g.getNode("closet").addItem("shoe");

        g.addNode("dungeon");
        g.getNode("dungeon").addItem("key");

        g.addNode("bathroom");
        g.getNode("bathroom").addItem("toothbrush");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("closet", "bathroom");

        return g;
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