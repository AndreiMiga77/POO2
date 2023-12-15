package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Album implements Playable {
    private String name;
    private int releaseYear;
    private String description;
    private ArrayList<Song> songs;
    private int listeners;

    public Album(String name, int releaseYear, String description, List<Song> songs) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.songs = new ArrayList<>(songs);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    @Override
    public int getDuration() {
        int duration = 0;
        for (Song song : songs) {
            duration += song.getDuration();
        }
        return duration;
    }

    @Override
    public int getNumTracks() {
        return songs.size();
    }

    @Override
    public Playable getTrack(int i) {
        return songs.get(i);
    }

    @Override
    public boolean allowsLike() {
        return false;
    }

    @Override
    public boolean allowsFollow() {
        return false;
    }

    @Override
    public boolean allowsShuffling() {
        return true;
    }

    @Override
    public boolean remembersTimestamp() {
        return false;
    }

    @Override
    public boolean isPlaylistRepeatable() {
        return true;
    }

    @Override
    public boolean isEligibleForPlaylist() {
        return true;
    }

    @Override
    public boolean isSeekable() {
        return false;
    }

    @Override
    public int getNumListeners() {
        return listeners;
    }

    @Override
    public void listen() {
        listeners++;
    }

    @Override
    public void stopListening() {
        listeners--;
    }

    public void delete() {
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            for (int j = song.getLikes() - 1; j >= 0; j--) {
                song.getLikers().get(j).unlikeSong(song);
            }
        }
    }
}
