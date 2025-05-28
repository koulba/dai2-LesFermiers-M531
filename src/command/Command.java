package command;

public interface Command {
    String getDescription();
    String getUsage();
    boolean hasValidArgument(String arg);
    void execute(String arg);
}