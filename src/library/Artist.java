package library;

import engine.ArtistPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Artist extends User {
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

    public void addAlbum(final Album album) {
        albums.add(album);
    }

    public void removeAlbum(final Album album) {
        albums.remove(album);
    }

    public List<Album> getAlbums() {
        return Collections.unmodifiableList(albums);
    }

    public void addEvent(final Event event) {
        events.add(event);
    }

    public void removeEvent(final Event event) {
        events.remove(event);
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public void addMerch(final Merch merch) {
        merchandise.add(merch);
    }

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
}
