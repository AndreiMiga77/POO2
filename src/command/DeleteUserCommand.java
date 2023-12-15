package command;

import command.output.CommandOutput;
import command.output.DeleteUserCommandOutput;
import library.*;

public class DeleteUserCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        String message = null;
        if (user == null) {
            message = "The username " + getUsername() + " doesn't exist.";
        } else if (user.getHomePage().getVisitors() != 0) {
            message = user.getUsername() + " can't be deleted.";
        } else if (user.getType() == User.UserType.USER) {
            library.deleteUser(user);
            message = user.getUsername() + " was successfully deleted.";
        } else if (user.getType() == User.UserType.ARTIST) {
            Artist artist = (Artist) user;
            for (Album album : artist.getAlbums()) {
                if (album.getNumListeners() != 0) {
                    message = user.getUsername() + " can't be deleted.";
                    break;
                }
                for (Song song : album.getSongs()) {
                    if (song.getNumListeners() != 0) {
                        message = user.getUsername() + " can't be deleted.";
                        break;
                    }
                }
                if (message != null) {
                    break;
                }
            }
            if (message == null) {
                library.deleteUser(user);
                message = user.getUsername() + " was successfully deleted.";
            }
        } else if (user.getType() == User.UserType.HOST) {
            Host host = (Host) user;
            for (Podcast podcast : host.getPodcasts()) {
                if (podcast.getNumListeners() != 0) {
                    message = user.getUsername() + " can't be deleted.";
                    break;
                }
            }
            if (message == null) {
                library.deleteUser(user);
                message = user.getUsername() + " was successfully deleted.";
            }
        }
        return new DeleteUserCommandOutput(getUsername(), getTimestamp(), message);
    }
}
