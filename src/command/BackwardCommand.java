package command;

import command.output.BackwardCommandOutput;
import command.output.CommandOutput;
import engine.Player;
import library.Library;
import library.User;

public final class BackwardCommand extends Command {
    public static final int NUM_SECONDS = 90;
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        String message = null;
        if (!player.isLoaded()) {
            message = "Please select a source before rewinding.";
        } else if (!player.getCurrent().isSeekable()) {
            message = "The loaded source is not a podcast.";
        } else {
            player.seekBackward(NUM_SECONDS);
            message = "Rewound successfully.";
        }
        return new BackwardCommandOutput(getUsername(), getTimestamp(), message);
    }
}
