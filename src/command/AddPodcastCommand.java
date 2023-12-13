package command;

import command.output.CommandOutput;
import fileio.input.EpisodeInput;

import java.util.ArrayList;

public class AddPodcastCommand extends Command {
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
        return null;
    }
}
