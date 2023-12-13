package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Playlist implements Playable {
    private String name;
    private int creationTime;
    private ArrayList<Song> songs;
    private boolean isPrivate;
    private int followers;

    public Playlist(final String name, final int time) {
        this.name = name;
        creationTime = time;
        songs = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    /**
     * Add a song
     * @param song song to add
     */
    public void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Remove a song
     * @param song song to remove
     */
    public void removeSong(final Song song) {
        songs.remove(song);
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setVisibility(final boolean isHidden) {
        this.isPrivate = isHidden;
    }

    public int getFollowers() {
        return followers;
    }

    /**
     * Receive a follower
     */
    public void addFollower() {
        followers++;
    }

    /**
     * Lose a follower
     */
    public void removeFollower() {
        followers--;
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
    public Playable getTrack(final int i) {
        return songs.get(i);
    }

    @Override
    public boolean allowsLike() {
        return false;
    }

    @Override
    public boolean allowsFollow() {
        return true;
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
    public boolean isSeekable() {
        return false;
    }
}
