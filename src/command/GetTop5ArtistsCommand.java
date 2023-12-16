package command;

import command.output.CommandOutput;
import command.output.GetTop5ArtistsCommandOutput;
import library.Artist;
import library.Library;

import java.util.ArrayList;

public final class GetTop5ArtistsCommand extends Command {
    public static final int NUM_MATCHES = 5;
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        ArrayList<Artist> artists = library.getArtists();
        ArrayList<String> artistNames = new ArrayList<>(NUM_MATCHES);

        artists.sort((s1, s2) -> -Integer.compare(s1.getTotalLikes(), s2.getTotalLikes()));
        for (int i = 0; i < Math.min(NUM_MATCHES, artists.size()); i++) {
            artistNames.add(artists.get(i).getUsername());
        }
        return new GetTop5ArtistsCommandOutput(getTimestamp(), artistNames);
    }
}
