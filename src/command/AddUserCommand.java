package command;

import command.output.AddUserCommandOutput;
import command.output.CommandOutput;
import library.Artist;
import library.Library;
import library.User;

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
        String message = null;
        if (library.findUser(getUsername()) == null) {
            switch (type) {
                case "user" -> library.addUser(new User(getUsername(), age, city, User.UserType.USER));
                case "host" -> library.addUser(new User(getUsername(), age, city, User.UserType.HOST));
                case "artist" -> library.addUser(new Artist(getUsername(), age, city));
            }
            message = "The username " + getUsername() + " has been added successfully.";
        } else {
            message = "The username " + getUsername() + " is already taken.";
        }
        return new AddUserCommandOutput(getUsername(), getTimestamp(), message);
    }
}
