package command;

import command.output.CommandOutput;

public class AddAnnouncementCommand extends Command {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}
