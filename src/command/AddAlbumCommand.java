package command;

import command.output.AddAlbumCommandOutput;
import command.output.CommandOutput;
import fileio.input.SongInput;
import library.Library;
import library.User;
import library.Artist;
import library.Album;
import library.Song;

import java.util.ArrayList;
import java.util.List;

public final class AddAlbumCommand extends Command {
    private String name;
    private Integer releaseYear;
    private String description;
    private ArrayList<SongInput> songs;

    public String getName() {
        return name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<SongInput> getSongs() {
        return songs;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        String message = null;
        if (user == null) {
            message = "The username " + getUsername() + " doesn't exist.";
        } else if (user.getType() != User.UserType.ARTIST) {
            message = getUsername() + " is not an artist.";
        } else {
            Artist artist = (Artist) user;
            List<Album> albums = artist.getAlbums();
            if (albums.stream().anyMatch(album -> album.getName().equals(name))) {
                message = getUsername() + " has another album with the same name.";
            } else {
                boolean duplicates = false;
                for (int i = 0; i < songs.size() - 1; i++) {
                    for (int j = i + 1; j < songs.size(); j++) {
                        if (songs.get(i).getName().equals(songs.get(j).getName())) {
                            duplicates = true;
                            i = songs.size();
                            break;
                        }
                    }
                }
                if (duplicates) {
                    message = getUsername() + " has the same song at least twice in this album.";
                } else {
                    ArrayList<Song> songObjects = new ArrayList<>();
                    for (SongInput song : songs) {
                        songObjects.add(new Song(song));
                    }
                    artist.addAlbum(new Album(name, releaseYear, description, songObjects));
                    message = getUsername() + " has added new album successfully.";
                }
            }
        }
        return new AddAlbumCommandOutput(getUsername(), getTimestamp(), message);
    }
}
