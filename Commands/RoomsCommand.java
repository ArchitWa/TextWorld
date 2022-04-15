package Commands;

import Graph.Graph;
import Player.Player;

public class RoomsCommand implements Command {
    private Player player;
    private Graph.Room currentRoom;

    public RoomsCommand(Player player) {
        this.player = player;
    }

    @Override
    public void init(String response) {
        this.currentRoom = player.getCurrentRoom();
    }

    @Override
    public boolean execute() {
        System.out.println(
                currentRoom.getNeighborNames().equals("") ?
                        "You cannot go anywhere" : "You can go to the: " + currentRoom.getNeighborNames());
        return true;
    }

    @Override
    public String getCommandWord() {
        return "rooms";
    }
}

