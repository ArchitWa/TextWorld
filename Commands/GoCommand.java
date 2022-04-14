package Commands;

import Player.Player;

public class GoCommand implements Command{
    private Player player;
    private String[] arr;

    public GoCommand(Player player) {
        this.player = player;
    }

    @Override
    public void init(String response) {
        this.arr = response.split(" ");
    }

    @Override
    public boolean execute() {
        if (player.moveToRoom(arr[1])) {
            System.out.println("You moved to: " + arr[1]);
            return true;
        } else {
            System.out.println("You cannot go there!");
            return false;
        }
    }

    @Override
    public String getCommandWord() {
        return "go";
    }
}
