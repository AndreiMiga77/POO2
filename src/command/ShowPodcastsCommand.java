package command;

import command.output.CommandOutput;

public class ShowPodcastsCommand extends Command {
    // What is this used for?
    private Integer playlistId;

    public Integer getPlaylistId() {
        return playlistId;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}
