package command;

import command.output.CommandOutput;
import command.output.SwitchConnectionStatusCommandOutput;
import library.Library;
import library.User;

public final class SwitchConnectionStatusCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        String message = null;
        if (user == null) {
            message = "The username " + getUsername() + " doesn't exist.";
        } else if (user.getType() != User.UserType.USER) {
            message = user.getUsername() + " is not a normal user";
        } else {
            user.setOffline(!user.isOffline());
            message = user.getUsername() + " has changed status successfully.";
        }
        return new SwitchConnectionStatusCommandOutput(getUsername(), getTimestamp(), message);
    }
}
