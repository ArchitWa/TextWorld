package Commands;

public interface Command {
    public void init(String response);

    public boolean execute();

    public String getCommandWord();
}
