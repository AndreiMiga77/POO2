package command;

import command.output.CommandOutput;

public class RemovePodcastCommand extends Command {
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}
