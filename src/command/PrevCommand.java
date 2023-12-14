package command;

import command.output.CommandOutput;
import command.output.PrevCommandOutput;
import engine.Player;
import library.Library;
import library.User;

public final class PrevCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        String message = null;
        if (user.isOffline()) {
            message = user.getUsername() + " is offline.";
        } else if (!player.isLoaded()) {
            message = "Please load a source before returning to the previous track.";
        } else {
            player.rewind();
            String name = player.getCurrentTrack().getName();
            message = "Returned to previous track successfully. The current track is " + name + ".";
        }
        return new PrevCommandOutput(getUsername(), getTimestamp(), message);
    }
}
