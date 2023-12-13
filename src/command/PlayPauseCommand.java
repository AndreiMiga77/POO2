package command;

import command.output.CommandOutput;
import command.output.PlayPauseCommandOutput;
import library.Library;
import library.User;

import engine.Player;

public final class PlayPauseCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        String message;
        if (!player.isLoaded()) {
            message = "Please load a source before attempting to pause or resume playback.";
        } else if (player.isPaused()) {
            player.resume();
            message = "Playback resumed successfully.";
        } else {
            player.pause();
            message = "Playback paused successfully.";
        }
        return new PlayPauseCommandOutput(getUsername(), getTimestamp(), message);
    }
}
