package library;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import fileio.input.SongInput;

public final class Song implements Playable {
    private final String name;
    private final int duration;
    private final String album;
    private final ArrayList<String> tags;
    private final String lyrics;
    private final String genre;
    private final int releaseYear;
    private final String artist;
    private int likes;

    public Song(final SongInput input) {
        name = input.getName();
        duration = input.getDuration();
        album = input.getAlbum();
        tags = input.getTags();
        lyrics = input.getLyrics();
        genre = input.getGenre();
        releaseYear = input.getReleaseYear();
        artist = input.getArtist();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public String getLyrics() {
        return lyrics;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getArtist() {
        return artist;
    }

    public int getLikes() {
        return likes;
    }

    /**
     * Receive a like
     */
    public void addLike() {
        likes++;
    }

    /**
     * Lose a like
     */
    public void removeLike() {
        likes--;
    }

    @Override
    public int getNumTracks() {
        return 1;
    }

    @Override
    public boolean allowsLike() {
        return true;
    }

    @Override
    public boolean allowsFollow() {
        return false;
    }

    @Override
    public boolean allowsShuffling() {
        return false;
    }

    @Override
    public boolean remembersTimestamp() {
        return false;
    }

    @Override
    public boolean isPlaylistRepeatable() {
        return false;
    }

    @Override
    public boolean isSeekable() {
        return false;
    }

    @Override
    public Playable getTrack(final int i) {
        if (i != 0) {
            return null;
        }
        return this;
    }
}
