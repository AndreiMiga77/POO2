package command;

import command.output.CommandOutput;
import command.output.LikeCommandOutput;
import engine.Player;
import library.Library;
import library.Song;
import library.User;

public final class LikeCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        String message;
        if (!player.isLoaded()) {
            message = "Please load a source before liking or unliking.";
        } else if (!player.getCurrentTrack().allowsLike()) {
            message = "Loaded source is not a song.";
        } else {
            Song song = (Song) player.getCurrentTrack();
            if (!user.hasLikedSong(song)) {
                user.likeSong(song);
                message = "Like registered successfully.";
            } else {
                user.unlikeSong(song);
                message = "Unlike registered successfully.";
            }
        }
        return new LikeCommandOutput(getUsername(), getTimestamp(), message);
    }
}
