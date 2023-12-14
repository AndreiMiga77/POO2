package command;

import command.output.AddUserCommandOutput;
import command.output.CommandOutput;
import library.*;

public final class AddUserCommand extends Command {
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
        Library library = Library.getInstance();
        User.UserType utype = User.UserType.USER;
        String message = null;
        switch (type) {
            case "artist":
                utype = User.UserType.ARTIST;
                break;
            case "host":
                utype = User.UserType.HOST;
                break;
        }
        if (library.findUser(getUsername()) == null) {
            library.addUser(new User(getUsername(), age, city, utype));
            message = "The username " + getUsername() + " has been added successfully.";
        } else {
            message = "The username " + getUsername() + " is already taken.";
        }
        return new AddUserCommandOutput(getUsername(), getTimestamp(), message);
    }
}
