package command;

import command.output.CommandOutput;
import fileio.input.SongInput;

import java.util.ArrayList;

public class AddAlbumCommand extends Command {
    private String name;
    private Integer releaseYear;
    private String description;
    private ArrayList<SongInput> songs;

    @Override
    public CommandOutput execute() {
        return null;
    }
}
