package command;

import command.output.CommandOutput;

public class AddUserCommand extends Command {
    private String type;
    private Integer age;
    private String city;

    public String getType() {
        return type;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}
