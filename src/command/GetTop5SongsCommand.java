package command;

import command.output.CommandOutput;
import command.output.GetTop5SongsCommandOutput;
import library.Library;
import library.Song;

import java.util.ArrayList;

public final class GetTop5SongsCommand extends Command {
    public static final int NUM_MATCHES = 5;
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        ArrayList<Song> songs = new ArrayList<>(library.getOrderedSongs());
        ArrayList<String> songNames = new ArrayList<>(NUM_MATCHES);

        songs.sort((s1, s2) -> {
            if (s1.getLikes() > s2.getLikes()) {
                return -1;
            } else if (s1.getLikes() < s2.getLikes()) {
                return 1;
            }
            return 0;
        });
        for (int i = 0; i < Math.min(NUM_MATCHES, songs.size()); i++) {
            songNames.add(songs.get(i).getName());
        }
        return new GetTop5SongsCommandOutput(getTimestamp(), songNames);
    }
}
