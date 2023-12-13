package command;

import command.output.CommandOutput;
import command.output.FollowCommandOutput;
import library.Library;
import library.Playable;
import library.Playlist;
import library.User;

import java.util.List;

public final class FollowCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        List<Playable> lastSearch = user.getLastSearch();
        String message;
        if (lastSearch == null || user.getSelectedSource() < 0) {
            message = "Please select a source before following or unfollowing.";
        } else {
            Playable selected = lastSearch.get(user.getSelectedSource());
            if (!selected.allowsFollow()) {
                message = "The selected source is not a playlist.";
            } else if (user.getPlaylists().contains((Playlist) selected)) {
                message = "You cannot follow or unfollow your own playlist.";
            } else {
                Playlist playlist = (Playlist) selected;
                if (!user.hasFollowedPlaylist(playlist)) {
                    user.followPlaylist(playlist);
                    message = "Playlist followed successfully.";
                } else {
                    user.unfollowPlaylist(playlist);
                    message = "Playlist unfollowed successfully.";
                }
            }
        }
        return new FollowCommandOutput(getUsername(), getTimestamp(), message);
    }
}
