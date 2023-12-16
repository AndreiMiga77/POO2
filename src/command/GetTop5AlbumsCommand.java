package command;

import command.output.CommandOutput;
import command.output.GetTop5AlbumsCommandOutput;
import library.Album;
import library.Artist;
import library.Library;

import java.util.ArrayList;

public final class GetTop5AlbumsCommand extends Command {
    public static final int NUM_MATCHES = 5;
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        ArrayList<Album> albums = new ArrayList<>();
        for (Artist a : library.getArtists()) {
            albums.addAll(a.getAlbums());
        }
        ArrayList<String> albumNames = new ArrayList<>(NUM_MATCHES);

        albums.sort((s1, s2) -> {
            if (s1.getTotalLikes() > s2.getTotalLikes()) {
                return -1;
            } else if (s1.getTotalLikes() < s2.getTotalLikes()) {
                return 1;
            } else {
                return s1.getName().compareTo(s2.getName());
            }
        });
        for (int i = 0; i < Math.min(NUM_MATCHES, albums.size()); i++) {
            albumNames.add(albums.get(i).getName());
        }
        return new GetTop5AlbumsCommandOutput(getTimestamp(), albumNames);
    }
}
