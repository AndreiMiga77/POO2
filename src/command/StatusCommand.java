package command;

import command.output.CommandOutput;
import command.output.StatusCommandOutput;
import engine.Player;
import library.Library;
import library.User;

import java.util.LinkedHashMap;

public final class StatusCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if (player.getCurrentTrack() == null) {
            map.put("name", "");
        } else {
            map.put("name", player.getCurrentTrack().getName());
        }
        map.put("remainedTime", player.getTimeRemaining());
        switch (player.getRepeatState()) {
            case NO_REPEAT:
                map.put("repeat", "No Repeat");
                break;
            case REPEAT_ALL:
                if (player.getCurrent().isPlaylistRepeatable()) {
                    map.put("repeat", "Repeat All");
                } else {
                    map.put("repeat", "Repeat Infinite");
                }
                break;
            case REPEAT_CURRENT:
                map.put("repeat", "Repeat Current Song");
                break;
            case REPEAT_ONCE:
                map.put("repeat", "Repeat Once");
                break;
            default:
                break;
        }

        map.put("shuffle", player.isShuffled());
        map.put("paused", player.isPaused());

        return new StatusCommandOutput(getUsername(), getTimestamp(), map);
    }
}
