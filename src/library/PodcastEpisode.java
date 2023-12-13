package library;

import fileio.input.EpisodeInput;

public final class PodcastEpisode implements Playable {
    private String name;
    private int duration;
    private String description;

    public PodcastEpisode(final EpisodeInput input) {
        name = input.getName();
        duration = input.getDuration();
        description = input.getDescription();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getNumTracks() {
        return 1;
    }

    @Override
    public Playable getTrack(final int i) {
        if (i != 0) {
            return null;
        }
        return this;
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
}
