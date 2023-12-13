package command;

import command.output.CommandOutput;
import command.output.GetTop5PlaylistsCommandOutput;
import library.Library;
import library.Playlist;

import java.util.ArrayList;

public final class GetTop5PlaylistsCommand extends Command {
    public static final int NUM_MATCHES = 5;
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        ArrayList<Playlist> playlists = library.getPublicPlaylists();
        ArrayList<String> playlistNames = new ArrayList<>(NUM_MATCHES);
        playlists.sort((p1, p2) -> {
            if (p1.getFollowers() > p2.getFollowers()) {
                return -1;
            } else if (p1.getFollowers() < p2.getFollowers()) {
                return 1;
            } else {
                if (p1.getCreationTime() < p2.getCreationTime()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < Math.min(NUM_MATCHES, playlists.size()); i++) {
            playlistNames.add(playlists.get(i).getName());
        }
        return new GetTop5PlaylistsCommandOutput(getTimestamp(), playlistNames);
    }
}
