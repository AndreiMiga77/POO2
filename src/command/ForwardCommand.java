package command;

import command.output.CommandOutput;
import command.output.ForwardCommandOutput;
import engine.Player;
import library.Library;
import library.User;

public final class ForwardCommand extends Command {
    public static final int NUM_SECONDS = 90;
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        String message = null;
        if (user.isOffline()) {
            message = user.getUsername() + " is offline.";
        } else if (!player.isLoaded()) {
            message = "Please load a source before attempting to forward.";
        } else if (!player.getCurrent().isSeekable()) {
            message = "The loaded source is not a podcast.";
        } else {
            player.seekForward(NUM_SECONDS);
            message = "Skipped forward successfully.";
        }
        return new ForwardCommandOutput(getUsername(), getTimestamp(), message);
    }
}
