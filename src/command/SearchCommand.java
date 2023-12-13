package command;

import command.output.CommandOutput;
import command.output.SearchCommandOutput;
import library.Library;
import library.User;
import library.Song;
import library.Podcast;
import library.Playlist;

import java.util.ArrayList;
import java.util.Map;

public final class SearchCommand extends Command {
    public static final int NUM_MATCHES = 5;
    private String type;
    private Map<String, Object> filters;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(final Map<String, Object> filters) {
        this.filters = filters;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        ArrayList<String> names = new ArrayList<>(NUM_MATCHES);
        String message = null;

        user.getPlayer().unload();
        user.setSelectedSource(-1);

        if (type.equals("song")) {
            ArrayList<Song> songs = library.findSongsByFilter(filters);
            int searchSize = Math.min(songs.size(), NUM_MATCHES);
            for (Song song : songs.subList(0, searchSize)) {
                names.add(song.getName());
            }
            user.setLastSearch(songs.subList(0, searchSize));
            message = "Search returned " + searchSize + " results";
        } else if (type.equals("podcast")) {
            ArrayList<Podcast> podcasts = library.findPodcastsByFilter(filters);
            int searchSize = Math.min(podcasts.size(), NUM_MATCHES);
            for (Podcast podcast : podcasts.subList(0, searchSize)) {
                names.add(podcast.getName());
            }
            user.setLastSearch(podcasts.subList(0, searchSize));
            message = "Search returned " + searchSize + " results";
        } else if (type.equals("playlist")) {
            ArrayList<Playlist> playlists = library.findPlaylistsByFilter(filters, user);
            int searchSize = Math.min(playlists.size(), NUM_MATCHES);
            for (Playlist playlist : playlists.subList(0, searchSize)) {
                names.add(playlist.getName());
            }
            user.setLastSearch(playlists.subList(0, searchSize));
            message = "Search returned " + searchSize + " results";
        }
        return new SearchCommandOutput(getUsername(), getTimestamp(), message, names);
    }
}
