package library;

import fileio.input.UserInput;

import engine.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public final class User {
    private String username;
    private int age;
    private String city;
    private List<? extends Playable> lastSearch;
    private int selectedSource;
    private Player player;

    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> ownedPlaylists;
    private ArrayList<Playlist> followedPlaylists;

    public User(final UserInput input) {
        username = input.getUsername();
        age = input.getAge();
        city = input.getCity();
        selectedSource = -1;
        player = new Player();
        likedSongs = new ArrayList<>();
        ownedPlaylists = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Get the last searched items
     */
    public List<Playable> getLastSearch() {
        if (lastSearch == null) {
            return null;
        }
        return Collections.unmodifiableList(lastSearch);
    }

    public void setLastSearch(final List<? extends Playable> lastSearch) {
        this.lastSearch = lastSearch;
    }

    public int getSelectedSource() {
        return selectedSource;
    }

    public void setSelectedSource(final int selectedSource) {
        this.selectedSource = selectedSource;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Create a new public playlist
     * @param name name of the playlist
     * @param timestamp creation time
     */
    public void createPlaylist(final String name, final int timestamp) {
        ownedPlaylists.add(new Playlist(name, timestamp));
    }

    /**
     * Get a playlist
     * @param id zero-based index
     */
    public Playlist getPlaylist(final int id) {
        if (id >= ownedPlaylists.size()) {
            return null;
        }
        return ownedPlaylists.get(id);
    }

    /**
     * Get a playlist
     * @param name playlist's name
     */
    public Playlist getPlaylist(final String name) {
        Stream<Playlist> stream = ownedPlaylists.stream();
        return stream.filter(playlist -> playlist.getName().equals(name)).findAny().orElse(null);
    }

    /**
     * Get all owned playlists
     */
    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(ownedPlaylists);
    }

    /**
     * Whether the song was liked by the user
     * @param song song to check
     */
    public boolean hasLikedSong(final Song song) {
        return likedSongs.contains(song);
    }

    /**
     * Like a song
     * @param song song to like
     */
    public void likeSong(final Song song) {
        if (!likedSongs.contains(song)) {
            likedSongs.add(song);
            song.addLike();
        }
    }

    /**
     * Unlike a song
     * @param song song to unlike
     */
    public void unlikeSong(final Song song) {
        if (likedSongs.remove(song)) {
            song.removeLike();
        }

    }

    /**
     * Get all liked songs
     */
    public List<Song> getLikedSongs() {
        return Collections.unmodifiableList(likedSongs);
    }

    /**
     * Whether the playlist was followed by the user
     * @param playlist playlist to check
     */
    public boolean hasFollowedPlaylist(final Playlist playlist) {
        return followedPlaylists.contains(playlist);
    }

    /**
     * Follow a playlist
     * @param playlist playlist to follow
     */
    public  void followPlaylist(final Playlist playlist) {
        if (!followedPlaylists.contains(playlist)) {
            followedPlaylists.add(playlist);
            playlist.addFollower();
        }
    }

    /**
     * Unfollow a playlist
     * @param playlist playlist to unfollow
     */
    public void unfollowPlaylist(final Playlist playlist) {
        if (followedPlaylists.remove(playlist)) {
            playlist.removeFollower();
        }
    }
}
