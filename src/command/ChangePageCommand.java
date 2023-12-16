package command;

import command.output.ChangePageCommandOutput;
import command.output.CommandOutput;
import engine.LikedContentPage;
import library.Library;
import library.User;

public final class ChangePageCommand extends Command {
    private String nextPage;

    public String getNextPage() {
        return nextPage;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        String message;
        if (user.isOffline()) {
            message = user.getUsername() + " is offline.";
        } else if (nextPage.equals("Home")) {
            user.setCurrentPage(user.getHomePage());
            message = getUsername() + " accessed Home successfully.";
        } else if (nextPage.equals("LikedContent")) {
            user.setCurrentPage(new LikedContentPage(user));
            message = getUsername() + " accessed LikedContent successfully.";
        } else {
            message = getUsername() + " is trying to access a non-existent page.";
        }
        return new ChangePageCommandOutput(getUsername(), getTimestamp(), message);
    }
}
