package Commands;

import Graph.Graph;
import Player.Player;

public class DropCommand implements Command {
    private Player player;
    private Graph.Room currentRoom;
    private String[] arr;

    public DropCommand(Player player) {
        this.player = player;
    }

    @Override
    public void init(String response) {
        this.arr = response.split(" ");
        this.currentRoom =player.getCurrentRoom();
    }

    @Override
    public boolean execute() {
        if (player.hasItem(arr[1])) {
            currentRoom.addItem(player.getItem(arr[1]));
            player.removeItem(arr[1]);
            System.out.println("You have dropped the " + arr[1] + " item");
            return true;
        } else {
            System.out.println("You do not have this item!");
            return false;
        }
    }

    @Override
    public String getCommandWord() {
        return "drop";
    }
}
