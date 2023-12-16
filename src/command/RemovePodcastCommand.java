package command;

import command.output.CommandOutput;
import command.output.RemovePodcastCommandOutput;
import library.Library;
import library.User;
import library.Host;
import library.Podcast;

import java.util.List;

public final class RemovePodcastCommand extends Command {
    private String name;

    public String getName() {
        return name;
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
            Podcast podcast = podcasts.stream().filter(p -> p.getName()
                    .equals(name)).findAny().orElse(null);
            if (podcast == null) {
                message = getUsername() + " doesn't have a podcast with the given name.";
            } else {
                if (podcast.getNumListeners() == 0) {
                    message = getUsername() + " deleted the podcast successfully.";
                    host.removePodcast(podcast);
                } else {
                    message = getUsername() + " can't delete this podcast.";
                }
            }
        }
        return new RemovePodcastCommandOutput(getUsername(), getTimestamp(), message);
    }
}
