package Commands;

import java.util.HashMap;

public class CommandParser {
    private HashMap<String, Command> commands;

    public CommandParser() {
        commands = new HashMap<>();
    }

    public void addCommand(Command c) {
        commands.put(c.getCommandWord(), c);
    }

    public Command parseCommandString(String response) {
        String[] arr = response.split(" ");
        String cmd = arr[0];

        Command c = commands.get(cmd);
        if (c != null) c.init(response);
        return c;
    }

    public void displayCommands() {
        System.out.println("You can use the following commands: \ngo [room name]\nlook\nadd room [directed/undirected] [room name]\nquit\ntake [item]\ndrop [item]\nrooms");
    }
}
