package command;

import command.output.AddRemoveInPlaylistCommandOutput;
import command.output.CommandOutput;
import engine.Player;
import library.Library;
import library.Playlist;
import library.Song;
import library.User;

import java.util.List;

public final class AddRemoveInPlaylistCommand extends Command {
    private Integer playlistId;

    public Integer getPlaylistId() {
        return playlistId;
    }

    @Override
    public CommandOutput execute() {
        int id = playlistId - 1;
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        Playlist playlist = user.getPlaylist(id);
        String message;
        if (user.isOffline()) {
            message = user.getUsername() + " is offline.";
        } else if (playlist == null) {
            message = "The specified playlist does not exist.";
        } else if (!player.isLoaded()) {
            message = "Please load a source before adding to or removing from the playlist.";
        } else if (!(player.getCurrent() instanceof Song)) {
            message = "The loaded source is not a song.";
        } else {
            List<Song> songs = playlist.getSongs();
            if (!songs.contains((Song) player.getCurrent())) {
                playlist.addSong((Song) player.getCurrent());
                message = "Successfully added to playlist.";
            } else {
                playlist.removeSong((Song) player.getCurrent());
                message = "Successfully removed from playlist.";
            }
        }
        return new AddRemoveInPlaylistCommandOutput(getUsername(), getTimestamp(), message);
    }
}
