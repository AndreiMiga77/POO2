package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Artist extends User {
    private ArrayList<Album> albums;

    public Artist(final String username, final int age, final String city) {
        super(username, age, city, UserType.ARTIST);
        albums = new ArrayList<>();
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }

    public List<Album> getAlbums() {
        return Collections.unmodifiableList(albums);
    }
}
