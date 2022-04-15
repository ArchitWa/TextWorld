package Commands;

import Graph.Graph;
import Player.Player;

public class TakeCommand implements Command {
    private Player player;
    private Graph.Room currentRoom;
    private String[] arr;

    public TakeCommand(Player player) {
        this.player = player;
    }

    @Override
    public void init(String response) {
        this.arr = response.split(" ");
        this.currentRoom =player.getCurrentRoom();
    }

    @Override
    public boolean execute() {
        if (currentRoom.hasItem(arr[1])) {
            player.addItem(currentRoom.getItem(arr[1]));
            currentRoom.removeItem(arr[1]);
            System.out.println("You have grabbed the " + arr[1] + " item");
            return true;
        } else {
            System.out.println("This room does not have this item!");
            return false;
        }
    }

    @Override
    public String getCommandWord() {
        return "take";
    }
}
