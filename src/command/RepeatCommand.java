package command;

import command.output.CommandOutput;
import command.output.RepeatCommandOutput;
import engine.Player;
import library.Library;
import library.User;

public final class RepeatCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        String message;
        if (!player.isLoaded()) {
            message = "Please load a source before setting the repeat status.";
        } else {
            Player.RepeatState rep = player.getRepeatState();
            if (player.getCurrent().isPlaylistRepeatable()) {
                if (rep == Player.RepeatState.NO_REPEAT) {
                    player.setRepeatState(Player.RepeatState.REPEAT_ALL);
                    message = "Repeat mode changed to repeat all.";
                } else if (rep == Player.RepeatState.REPEAT_ALL) {
                    player.setRepeatState((Player.RepeatState.REPEAT_CURRENT));
                    message = "Repeat mode changed to repeat current song.";
                } else {
                    player.setRepeatState(Player.RepeatState.NO_REPEAT);
                    message = "Repeat mode changed to no repeat.";
                }
            } else {
                if (rep == Player.RepeatState.NO_REPEAT) {
                    player.setRepeatState(Player.RepeatState.REPEAT_ONCE);
                    message = "Repeat mode changed to repeat once.";
                } else if (rep == Player.RepeatState.REPEAT_ONCE) {
                    player.setRepeatState((Player.RepeatState.REPEAT_ALL));
                    message = "Repeat mode changed to repeat infinite.";
                } else {
                    player.setRepeatState(Player.RepeatState.NO_REPEAT);
                    message = "Repeat mode changed to no repeat.";
                }
            }
        }
        return new RepeatCommandOutput(getUsername(), getTimestamp(), message);
    }
}
