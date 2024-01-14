package library;

import engine.ArtistPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Artist extends User {
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merchandise;

    public Artist(final String username, final int age, final String city) {
        super(username, age, city);
        setType(UserType.ARTIST);
        albums = new ArrayList<>();
        events = new ArrayList<>();
        merchandise = new ArrayList<>();
        homePage = new ArtistPage(this);
        currentPage = homePage;
    }

    /**
     * Add an album for this artist
     */
    public void addAlbum(final Album album) {
        albums.add(album);
        Library.getInstance().addAlbum(album);
    }

    /**
     * Remove an album from this artist
     */
    public void removeAlbum(final Album album) {
        albums.remove(album);
        album.delete();
    }

    public List<Album> getAlbums() {
        return Collections.unmodifiableList(albums);
    }

    /**
     * Add an event to the artist's page
     */
    public void addEvent(final Event event) {
        events.add(event);
    }

    /**
     * Remove an event from the artist's page
     */
    public void removeEvent(final Event event) {
        events.remove(event);
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    /**
     * Add a piece of merch to the artist's page
     */
    public void addMerch(final Merch merch) {
        merchandise.add(merch);
    }

    /**
     * Remove a piece of merch from the artist's page
     */
    public void removeMerch(final Merch merch) {
        merchandise.remove(merch);
    }

    public List<Merch> getMerchItems() {
        return Collections.unmodifiableList(merchandise);
    }

    @Override
    public void delete() {
        super.delete();
        for (int i = 0; i < albums.size(); i++) {
            albums.get(i).delete();
        }
    }

    /**
     * Get the total number of likes from all songs on all albums
     */
    public int getTotalLikes() {
        int likes = 0;
        for (Album a : albums) {
            likes += a.getTotalLikes();
        }
        return likes;
    }

    /**
     * Get a list of all songs from all albums
     */
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        for (Album a : albums) {
            songs.addAll(a.getSongs());
        }
        return songs;
    }
}
