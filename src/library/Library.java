package library;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashSet;
import java.util.stream.Stream;

import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;

public final class Library {
    private static Library instance;

    /**
     * Create a global library instance
     */
    public static void createLibrary(final LibraryInput libInput) {
        instance = new Library(libInput);
    }

    public static Library getInstance() {
        return instance;
    }

    private ArrayList<User> users;
    private ArrayList<Song> songs;

    private ArrayList<Podcast> podcasts;
    private ArrayList<Album> albums;

    private Library(final LibraryInput libInput) {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        albums = new ArrayList<>();
        for (UserInput user : libInput.getUsers()) {
            users.add(new User(user));
        }
        for (SongInput song : libInput.getSongs()) {
            songs.add(new Song(song));
        }
        for (PodcastInput podcast : libInput.getPodcasts()) {
            podcasts.add(new Podcast(podcast));
        }
    }

    /**
     * Get a list of all users (of all types)
     */
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    /**
     * Get a list of all songs on the platform
     */
    public ArrayList<Song> getSongs() {
        ArrayList<Song> allSongs = new ArrayList<>(songs);
        for (Artist u : getArtists()) {
            for (Album a : u.getAlbums()) {
                allSongs.addAll(a.getSongs());
            }
        }
        return allSongs;
    }

    /**
     * Get songs in the order they were added
     */
    public ArrayList<Song> getOrderedSongs() {
        ArrayList<Song> allSongs = new ArrayList<>(songs);
        for (Album a : albums) {
            allSongs.addAll(a.getSongs());
        }
        return allSongs;
    }

    public List<Podcast> getPodcasts() {
        return Collections.unmodifiableList(podcasts);
    }

    /**
     * Get a list of all regular users
     */
    public ArrayList<User> getRegularUsers() {
        ArrayList<User> regularUsers = new ArrayList<>();
        for (User u : users) {
            if (u.getType() == User.UserType.USER) {
                regularUsers.add(u);
            }
        }
        return regularUsers;
    }

    /**
     * Get a list of all artists
     */
    public ArrayList<Artist> getArtists() {
        ArrayList<Artist> artists = new ArrayList<>();
        for (User u : users) {
            if (u.getType() == User.UserType.ARTIST) {
                artists.add((Artist) u);
            }
        }
        return artists;
    }

    /**
     * Get a list of all hosts
     */
    public ArrayList<Host> getHosts() {
        ArrayList<Host> hosts = new ArrayList<>();
        for (User u : users) {
            if (u.getType() == User.UserType.HOST) {
                hosts.add((Host) u);
            }
        }
        return hosts;
    }

    /**
     * Get a list of all public playlists owned by all users
     */
    public ArrayList<Playlist> getPublicPlaylists() {
        ArrayList<Playlist> playlists = new ArrayList<>();
        for (User u : users) {
            playlists.addAll(u.getPlaylists());
        }
        playlists.removeIf(Playlist::isPrivate);
        return playlists;
    }

    /**
     * Add an album to the library
     */
    public void addAlbum(final Album album) {
        albums.add(album);
    }

    /**
     * Remove an album from the library
     */
    public void removeAlbum(final Album album) {
        albums.remove(album);
    }

    /**
     * Advance the time for each user
     * @param dif number of seconds to advance time
     */
    public void tickTime(final int dif) {
        for (User u : users) {
            u.tickTime(dif);
        }
    }

    /**
     * Add a user
     * @param user user object
     */
    public void addUser(final User user) {
        users.add(user);
    }

    /**
     * Find a user
     * @param username user's username
     */
    public User findUser(final String username) {
        Stream<User> stream = users.stream();
        return stream.filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }

    /**
     * Find songs that meet certain criteria
     * @param filters criteria to apply
     */
    public ArrayList<Song> findSongsByFilter(final Map<String, Object> filters) {
        ArrayList<Song> filteredSongs = new ArrayList<>(songs);
        for (User user : users) {
            if (user.getType() == User.UserType.ARTIST) {
                Artist artist = (Artist) user;
                for (Album album : artist.getAlbums()) {
                    filteredSongs.addAll(album.getSongs());
                }
            }
        }
        if (filters.containsKey("name")) {
            String name = (String) filters.get("name");
            filteredSongs.removeIf(song -> !song.getName().startsWith(name));
        }
        if (filters.containsKey("album")) {
            String album = (String) filters.get("album");
            filteredSongs.removeIf(song -> !song.getAlbum().equals(album));
        }
        if (filters.containsKey("tags")) {
            List<String> tags = (List<String>) filters.get("tags");
            filteredSongs.removeIf(song -> !new HashSet<>(song.getTags()).containsAll(tags));
        }
        if (filters.containsKey("lyrics")) {
            String lyric = (String) filters.get("lyrics");
            String lyricLower = lyric.toLowerCase();
            filteredSongs.removeIf(song -> !song.getLyrics().toLowerCase().contains(lyricLower));
        }
        if (filters.containsKey("genre")) {
            String genre = (String) filters.get("genre");
            filteredSongs.removeIf(song -> song.getGenre().compareToIgnoreCase(genre) != 0);
        }
        if (filters.containsKey("releaseYear")) {
            String yearCondition = (String) filters.get("releaseYear");
            int year = Integer.parseInt(yearCondition.substring(1));
            if (yearCondition.charAt(0) == '<') {
                filteredSongs.removeIf(song -> song.getReleaseYear() >= year);
            } else {
                filteredSongs.removeIf(song -> song.getReleaseYear() <= year);
            }
        }
        if (filters.containsKey("artist")) {
            String artist = (String) filters.get("artist");
            filteredSongs.removeIf(song -> !song.getArtist().equals(artist));
        }
        return filteredSongs;
    }

    /**
     * Find podcasts that meet certain criteria
     * @param filters criteria to apply
     */
    public ArrayList<Podcast> findPodcastsByFilter(final Map<String, Object> filters) {
        ArrayList<Podcast> filteredPodcasts = new ArrayList<>(podcasts);
        for (User user : users) {
            if (user.getType() == User.UserType.HOST) {
                Host host = (Host) user;
                filteredPodcasts.addAll(host.getPodcasts());
            }
        }
        if (filters.containsKey("name")) {
            String name = (String) filters.get("name");
            filteredPodcasts.removeIf(podcast -> !podcast.getName().startsWith(name));
        }
        if (filters.containsKey("owner")) {
            String owner = (String) filters.get("owner");
            filteredPodcasts.removeIf(podcast -> !podcast.getOwner().equals(owner));
        }
        return filteredPodcasts;
    }

    /**
     * Find playlists that meet certain criteria
     * @param filters criteria to apply
     * @param user private playlists are included only from this user
     */
    public ArrayList<Playlist> findPlaylistsByFilter(final Map<String, Object> filters,
                                                     final User user) {
        ArrayList<Playlist> filteredPlaylists = new ArrayList<>();

        for (User u : users) {
            if (filters.containsKey("owner") && !filters.get("owner").equals(u.getUsername())) {
                continue;
            }
            ArrayList<Playlist> userPlaylists = new ArrayList<>(u.getPlaylists());
            if (!u.equals(user)) {
                userPlaylists.removeIf(Playlist::isPrivate);
            }
            if (filters.containsKey("name")) {
                String name = (String) filters.get("name");
                userPlaylists.removeIf(playlist -> !playlist.getName().startsWith(name));
            }
            filteredPlaylists.addAll(userPlaylists);
        }
        return filteredPlaylists;
    }

    /**
     * Find albums that meet certain criteria
     * @param filters criteria to apply
     */
    public ArrayList<Album> findAlbumsByFilter(final Map<String, Object> filters) {
        ArrayList<Album> filteredAlbums = new ArrayList<>();
        for (User u : users) {
            if (u.getType() != User.UserType.ARTIST) {
                continue;
            }
            Artist a = (Artist) u;
            if (filters.containsKey("owner") && !filters.get("owner").equals(a.getUsername())) {
                continue;
            }
            filteredAlbums.addAll(a.getAlbums());
        }
        if (filters.containsKey("name")) {
            String name = (String) filters.get("name");
            filteredAlbums.removeIf(album -> !album.getName().startsWith(name));
        }
        if (filters.containsKey("description")) {
            String description = (String) filters.get("description");
            filteredAlbums.removeIf(album -> !album.getName().startsWith(description));
        }
        return filteredAlbums;
    }

    /**
     * Delete a user
     * @param user user to delete
     */
    public void deleteUser(final User user) {
        if (users.contains(user)) {
            users.remove(user);
            user.delete();
        }
    }
}
