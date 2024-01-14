package library;

import engine.HomePage;
import engine.Page;
import fileio.input.UserInput;

import engine.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class User {
    public enum UserType {
        USER,
        ARTIST,
        HOST,
    }
    private String username;
    private int age;
    private String city;
    private List<? extends Playable> lastSearch;
    private List<? extends Page> lastSearchedPages;
    private int selectedSource;
    private Player player;

    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> ownedPlaylists;
    private ArrayList<Playlist> followedPlaylists;
    private UserType type;
    private boolean offline;
    protected Page homePage;
    protected Page currentPage;

    public User(final String username, final int age, final String city) {
        this.username = username;
        this.age = age;
        this.city = city;
        this.type = type;
        selectedSource = -1;
        player = new Player();
        likedSongs = new ArrayList<>();
        ownedPlaylists = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        type = UserType.USER;
        offline = false;
        homePage = new HomePage(this);
        currentPage = homePage;
    }

    public User(final UserInput input) {
        username = input.getUsername();
        age = input.getAge();
        city = input.getCity();
        selectedSource = -1;
        player = new Player();
        likedSongs = new ArrayList<>();
        ownedPlaylists = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        type = UserType.USER;
        offline = false;
        homePage = new HomePage(this);
        currentPage = homePage;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(final String username) {
        this.username = username;
    }

    public final int getAge() {
        return age;
    }

    public final void setAge(final int age) {
        this.age = age;
    }

    public final String getCity() {
        return city;
    }

    public final void setCity(final String city) {
        this.city = city;
    }

    public final UserType getType() {
        return type;
    }

    public final void setType(final UserType type) {
        this.type = type;
    }

    public final Page getHomePage() {
        return homePage;
    }

    public final Page getCurrentPage() {
        return currentPage;
    }

    /**
     * Set the page that the user is visiting
     * @param page the page
     */
    public final void setCurrentPage(final Page page) {
        currentPage.unvisit();
        currentPage = page;
        page.visit();
    }

    /**
     * Get the last searched items
     */
    public final List<Playable> getLastSearch() {
        if (lastSearch == null) {
            return null;
        }
        return Collections.unmodifiableList(lastSearch);
    }

    public final void setLastSearch(final List<? extends Playable> lastSearch) {
        this.lastSearch = lastSearch;
    }

    /**
     * Results of the last "search" command for a page
     */
    public final List<Page> getLastSearchedPages() {
        if (lastSearchedPages == null) {
            return null;
        }
        return Collections.unmodifiableList(lastSearchedPages);
    }

    public final void setLastSearchedPages(final List<? extends Page> lastSearchedPages) {
        this.lastSearchedPages = lastSearchedPages;
    }

    public final int getSelectedSource() {
        return selectedSource;
    }

    public final void setSelectedSource(final int selectedSource) {
        this.selectedSource = selectedSource;
    }

    public final Player getPlayer() {
        return player;
    }

    /**
     * Create a new public playlist
     * @param name name of the playlist
     * @param timestamp creation time
     */
    public final void createPlaylist(final String name, final int timestamp) {
        ownedPlaylists.add(new Playlist(this, name, timestamp));
    }

    /**
     * Get a playlist
     * @param id zero-based index
     */
    public final Playlist getPlaylist(final int id) {
        if (id >= ownedPlaylists.size()) {
            return null;
        }
        return ownedPlaylists.get(id);
    }

    /**
     * Get a playlist
     * @param name playlist's name
     */
    public final Playlist getPlaylist(final String name) {
        Stream<Playlist> stream = ownedPlaylists.stream();
        return stream.filter(playlist -> playlist.getName().equals(name)).findAny().orElse(null);
    }

    /**
     * Get all owned playlists
     */
    public final List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(ownedPlaylists);
    }

    /**
     * Advance the time for the user's player
     * @param dif number of seconds to advance time
     */
    public final void tickTime(final int dif) {
        if (!offline) {
            player.tickTime(dif);
        }
    }

    /**
     * Whether the song was liked by the user
     * @param song song to check
     */
    public final boolean hasLikedSong(final Song song) {
        return likedSongs.contains(song);
    }

    /**
     * Like a song
     * @param song song to like
     */
    public final void likeSong(final Song song) {
        if (!likedSongs.contains(song)) {
            likedSongs.add(song);
            song.addLike(this);
        }
    }

    /**
     * Unlike a song
     * @param song song to unlike
     */
    public final void unlikeSong(final Song song) {
        if (likedSongs.remove(song)) {
            song.removeLike(this);
        }

    }

    /**
     * Get all liked songs
     */
    public final List<Song> getLikedSongs() {
        return Collections.unmodifiableList(likedSongs);
    }

    /**
     * Whether the playlist was followed by the user
     * @param playlist playlist to check
     */
    public final boolean hasFollowedPlaylist(final Playlist playlist) {
        return followedPlaylists.contains(playlist);
    }

    /**
     * Follow a playlist
     * @param playlist playlist to follow
     */
    public final void followPlaylist(final Playlist playlist) {
        if (!followedPlaylists.contains(playlist)) {
            followedPlaylists.add(playlist);
            playlist.addFollower(this);
        }
    }

    /**
     * Unfollow a playlist
     * @param playlist playlist to unfollow
     */
    public final void unfollowPlaylist(final Playlist playlist) {
        if (followedPlaylists.remove(playlist)) {
            playlist.removeFollower(this);
        }
    }

    /**
     * Get all followed playlists
     */
    public final List<Playlist> getFollowedPlaylists() {
        return Collections.unmodifiableList(followedPlaylists);
    }

    public final boolean isOffline() {
        return offline;
    }

    public final void setOffline(final boolean offline) {
        this.offline = offline;
    }

    /**
     * Delete the user
     */
    public void delete() {
        player.unload();
        for (int i = 0; i < likedSongs.size(); i++) {
            unlikeSong(likedSongs.get(i));
        }
        for (int i = 0; i < followedPlaylists.size(); i++) {
            unfollowPlaylist(followedPlaylists.get(i));
        }
        for (int i = 0; i < ownedPlaylists.size(); i++) {
            ownedPlaylists.get(i).delete();
        }

        Library library = Library.getInstance();
        ArrayList<User> users = library.getRegularUsers();
        for (User u : users) {
            if (u == this)
                continue;
            List<Page> pages = u.getLastSearchedPages();
            if (pages != null) {
                for (Page p : pages) {
                    if (p.getOwner() == this) {
                        u.setLastSearchedPages(null);
                        break;
                    }
                }
            }
        }
    }
}
