package engine;

import command.Command;
import command.output.CommandOutput;
import library.Library;

public final class CommandProcessor {
    private static CommandProcessor instance;

    /**
     * Create a global command processor instance
     */
    public static void createCommandProcessor() {
        instance = new CommandProcessor();
    }

    public static CommandProcessor getInstance() {
        return instance;
    }

    private int timestamp;

    private CommandProcessor() {
    }

    /**
     * Execute a command
     * @param cmd command to execute
     */
    public CommandOutput execute(final Command cmd) {
        int time = cmd.getTimestamp();
        int difTime = time - timestamp;
        timestamp = time;
        Library.getInstance().tickTime(difTime);
        return cmd.execute();
    }
}
