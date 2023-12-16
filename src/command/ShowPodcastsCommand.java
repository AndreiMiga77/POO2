package command;

import command.output.CommandOutput;
import command.output.ShowPodcastsCommandOutput;
import library.Library;
import library.User;
import library.Host;
import library.Podcast;
import library.PodcastEpisode;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class ShowPodcastsCommand extends Command {
    // What is this used for?
    private Integer playlistId;

    public Integer getPlaylistId() {
        return playlistId;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        if (user == null || user.getType() != User.UserType.HOST) {
            return null;
        } else {
            Host host = (Host) user;
            ArrayList<LinkedHashMap<String, Object>> podcastData = new ArrayList<>();
            for (Podcast podcast : host.getPodcasts()) {
                LinkedHashMap<String, Object> podcastHashMap = new LinkedHashMap<>();
                podcastHashMap.put("name", podcast.getName());
                ArrayList<String> episodeNames = new ArrayList<>();
                for (PodcastEpisode episode : podcast.getEpisodes()) {
                    episodeNames.add(episode.getName());
                }
                podcastHashMap.put("episodes", episodeNames);
                podcastData.add(podcastHashMap);
            }
            return new ShowPodcastsCommandOutput(getUsername(), getTimestamp(), podcastData);
        }
    }
}
