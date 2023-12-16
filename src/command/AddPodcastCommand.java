package command;

import command.output.AddPodcastCommandOutput;
import command.output.CommandOutput;
import fileio.input.EpisodeInput;
import library.Library;
import library.User;
import library.Host;
import library.Podcast;

import java.util.ArrayList;
import java.util.List;

public final class AddPodcastCommand extends Command {
    private String name;
    private ArrayList<EpisodeInput> episodes;

    public String getName() {
        return name;
    }

    public ArrayList<EpisodeInput> getEpisodes() {
        return episodes;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        String message = null;
        if (user == null) {
            message = "The username " + getUsername() + " doesn't exist.";
        } else if (user.getType() != User.UserType.HOST) {
            message = getUsername() + " is not a host.";
        } else {
            Host host = (Host) user;
            List<Podcast> podcasts = host.getPodcasts();
            if (podcasts.stream().anyMatch(podcast -> podcast.getName().equals(name))) {
                message = getUsername() + " has another podcast with the same name.";
            } else {
                boolean duplicates = false;
                for (int i = 0; i < episodes.size() - 1; i++) {
                    for (int j = i + 1; j < episodes.size(); j++) {
                        if (episodes.get(i).getName().equals(episodes.get(j).getName())) {
                            duplicates = true;
                            i = episodes.size();
                            break;
                        }
                    }
                }
                if (duplicates) {
                    message = getUsername() + " has the same episode in this podcast.";
                } else {
                    host.addPodcast(new Podcast(name, getUsername(), episodes));
                    message = getUsername() + " has added new podcast successfully.";
                }
            }
        }
        return new AddPodcastCommandOutput(getUsername(), getTimestamp(), message);
    }
}
