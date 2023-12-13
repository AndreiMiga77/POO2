package command;

import command.output.CommandOutput;
import command.output.ShuffleCommandOutput;
import engine.Player;
import library.Library;
import library.User;

public final class ShuffleCommand extends Command {
    private Long seed;

    public Long getSeed() {
        return seed;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        String message = null;
        if (!player.isLoaded()) {
            message = "Please load a source before using the shuffle function.";
        } else if (!player.getCurrent().allowsShuffling()) {
            message = "The loaded source is not a playlist.";
        } else {
            if (player.isShuffled()) {
                player.unshuffle();
                message = "Shuffle function deactivated successfully.";
            } else {
                player.shuffle(seed);
                message = "Shuffle function activated successfully.";
            }
        }
        return new ShuffleCommandOutput(getUsername(), getTimestamp(), message);
    }
}
