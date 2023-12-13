package command;

import command.output.CommandOutput;
import command.output.NextCommandOutput;
import engine.Player;
import library.Library;
import library.User;

public final class NextCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        String message = null;
        if (!player.isLoaded()) {
            message = "Please load a source before skipping to the next track.";
        } else {
            player.skip();
            String name;
            if (player.isLoaded()) {
                name = player.getCurrentTrack().getName();
                message = "Skipped to next track successfully. The current track is " + name + ".";
            } else {
                message = "Please load a source before skipping to the next track.";
            }
        }
        return new NextCommandOutput(getUsername(), getTimestamp(), message);
    }
}
