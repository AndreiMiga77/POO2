package command;

import command.output.CommandOutput;
import command.output.PrintCurrentPageCommandOutput;
import library.Library;
import library.User;

public final class PrintCurrentPageCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        String message = null;
        if (user.isOffline()) {
            message = getUsername() + " is offline.";
        } else {
            message = user.getCurrentPage().getContents();
        }
        return new PrintCurrentPageCommandOutput(getUsername(), getTimestamp(), message);
    }
}
