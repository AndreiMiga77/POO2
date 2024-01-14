package command;

import command.output.CommandOutput;
import command.output.SearchCommandOutput;
import engine.Page;
import library.Library;
import library.User;
import library.Song;
import library.Playable;
import library.Podcast;
import library.Playlist;
import library.Album;
import library.Artist;
import library.Host;

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

        if (user.isOffline()) {
            message = user.getUsername() + " is offline.";
            return new SearchCommandOutput(getUsername(),
                    getTimestamp(),
                    message,
                    new ArrayList<>());
        }

        user.getPlayer().unload();
        if (user.getSelectedSource() >= 0 && user.getLastSearch() != null) {
            for (Playable p : user.getLastSearch()) {
                p.stopListening();
            }
            user.setSelectedSource(-1);
        }
        user.setLastSearch(null);
        user.setLastSearchedPages(null);

        if (type.equals("song")) {
            ArrayList<Song> songs = library.findSongsByFilter(filters);
            int searchSize = Math.min(songs.size(), NUM_MATCHES);
            for (Song song : songs.subList(0, searchSize)) {
                names.add(song.getName());
            }
            user.setLastSearch(songs.subList(0, searchSize));
            for (Playable p : user.getLastSearch()) {
                p.listen();
            }
            message = "Search returned " + searchSize + " results";
        } else if (type.equals("podcast")) {
            ArrayList<Podcast> podcasts = library.findPodcastsByFilter(filters);
            int searchSize = Math.min(podcasts.size(), NUM_MATCHES);
            for (Podcast podcast : podcasts.subList(0, searchSize)) {
                names.add(podcast.getName());
            }
            user.setLastSearch(podcasts.subList(0, searchSize));
            for (Playable p : user.getLastSearch()) {
                p.listen();
            }
            message = "Search returned " + searchSize + " results";
        } else if (type.equals("playlist")) {
            ArrayList<Playlist> playlists = library.findPlaylistsByFilter(filters, user);
            int searchSize = Math.min(playlists.size(), NUM_MATCHES);
            for (Playlist playlist : playlists.subList(0, searchSize)) {
                names.add(playlist.getName());
            }
            user.setLastSearch(playlists.subList(0, searchSize));
            for (Playable p : user.getLastSearch()) {
                p.listen();
            }
            message = "Search returned " + searchSize + " results";
        } else if (type.equals("album")) {
            ArrayList<Album> albums = library.findAlbumsByFilter(filters);
            int searchSize = Math.min(albums.size(), NUM_MATCHES);
            for (Album album : albums.subList(0, searchSize)) {
                names.add(album.getName());
            }
            user.setLastSearch(albums.subList(0, searchSize));
            for (Playable p : user.getLastSearch()) {
                p.listen();
            }
            message = "Search returned " + searchSize + " results";
        } else if (type.equals("artist")) {
            ArrayList<Artist> artists = library.getArtists();
            if (filters.containsKey("name")) {
                String name = (String) filters.get("name");
                artists.removeIf(artist -> !artist.getUsername().startsWith(name));
            }
            int searchSize = Math.min(artists.size(), NUM_MATCHES);
            ArrayList<Page> pages = new ArrayList<>(searchSize);
            for (Artist artist : artists.subList(0, searchSize)) {
                names.add(artist.getUsername());
                pages.add(artist.getHomePage());
            }
            user.setLastSearchedPages(pages);
            message = "Search returned " + searchSize + " results";
        } else if (type.equals("host")) {
            ArrayList<Host> hosts = library.getHosts();
            if (filters.containsKey("name")) {
                String name = (String) filters.get("name");
                hosts.removeIf(host -> !host.getUsername().startsWith(name));
            }
            int searchSize = Math.min(hosts.size(), NUM_MATCHES);
            ArrayList<Page> pages = new ArrayList<>(searchSize);
            for (Host host : hosts.subList(0, searchSize)) {
                names.add(host.getUsername());
                pages.add(host.getHomePage());
            }
            user.setLastSearchedPages(pages);
            message = "Search returned " + searchSize + " results";
        }
        return new SearchCommandOutput(getUsername(), getTimestamp(), message, names);
    }
}
