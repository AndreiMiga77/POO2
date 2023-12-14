package command;

import command.output.CommandOutput;
import command.output.SwitchVisibilityCommandOutput;
import library.Library;
import library.Playlist;
import library.User;

import java.util.List;

public final class SwitchVisibilityCommand extends Command {
    private Integer playlistId;

    public Integer getPlaylistId() {
        return playlistId;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        List<Playlist> playlists = user.getPlaylists();
        String message;
        if (user.isOffline()) {
            message = user.getUsername() + " is offline.";
        } else if (playlistId > playlists.size()) {
            message = "The specified playlist ID is too high.";
        } else {
            Playlist playlist = playlists.get(playlistId - 1);
            if (playlist.isPrivate()) {
                message = "Visibility status updated successfully to public.";
                playlist.setVisibility(false);
            } else {
                message = "Visibility status updated successfully to private.";
                playlist.setVisibility(true);
            }
        }
        return new SwitchVisibilityCommandOutput(getUsername(), getTimestamp(), message);
    }
}
