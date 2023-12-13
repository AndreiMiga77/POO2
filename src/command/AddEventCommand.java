package command;

import command.output.CommandOutput;

public class AddEventCommand extends Command {
    private String name;
    private String description;
    private String date;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}
