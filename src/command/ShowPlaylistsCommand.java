package command;

import command.output.CommandOutput;
import command.output.ShowPlaylistsCommandOutput;
import library.Library;
import library.Playlist;
import library.Song;
import library.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class ShowPlaylistsCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        ArrayList<LinkedHashMap<String, Object>> list = new ArrayList<>();

        for (Playlist playlist : user.getPlaylists()) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("name", playlist.getName());
            ArrayList<String> songNames = new ArrayList<>();
            for (Song song : playlist.getSongs()) {
                songNames.add(song.getName());
            }
            map.put("songs", songNames);
            if (playlist.isPrivate()) {
                map.put("visibility", "private");
            } else {
                map.put("visibility", "public");
            }
            map.put("followers", playlist.getFollowers());
            list.add(map);
        }
        return new ShowPlaylistsCommandOutput(getUsername(), getTimestamp(), list);
    }
}
