package library;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import fileio.input.PodcastInput;
import fileio.input.EpisodeInput;

public final class Podcast implements Playable {
    private String name;
    private String owner;

    private ArrayList<PodcastEpisode> episodes;

    public Podcast(final PodcastInput input) {
        name = input.getName();
        owner = input.getOwner();

        episodes = new ArrayList<>();
        for (EpisodeInput episode : input.getEpisodes()) {
            episodes.add(new PodcastEpisode(episode));
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public List<PodcastEpisode> getEpisodes() {
        return Collections.unmodifiableList(episodes);
    }

    @Override
    public int getDuration() {
        int duration = 0;
        for (PodcastEpisode episode : episodes) {
            duration += episode.getDuration();
        }
        return duration;
    }

    @Override
    public int getNumTracks() {
        return episodes.size();
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
        return true;
    }

    @Override
    public boolean isPlaylistRepeatable() {
        return false;
    }

    @Override
    public boolean isSeekable() {
        return true;
    }

    @Override
    public Playable getTrack(final int i) {
        return episodes.get(i);
    }
}
