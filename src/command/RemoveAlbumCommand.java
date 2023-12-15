package command;

import command.output.AddAlbumCommandOutput;
import command.output.CommandOutput;
import command.output.RemoveAlbumCommandOutput;
import fileio.input.SongInput;
import library.*;

import java.util.ArrayList;
import java.util.List;

public class RemoveAlbumCommand extends Command {
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
        } else if (user.getType() != User.UserType.ARTIST) {
            message = getUsername() + " is not an artist.";
        } else {
            Artist artist = (Artist) user;
            List<Album> albums = artist.getAlbums();
            Album album = albums.stream().filter(a -> a.getName().equals(name)).findAny().orElse(null);
            if (album == null) {
                message = getUsername() + " doesn't have an album with the given name.";
            } else {
                boolean canRemove = true;
                if (album.getNumListeners() > 0) {
                    canRemove = false;
                } else {
                    for (Song s : album.getSongs()) {
                        if (s.getNumListeners() > 0) {
                            canRemove = false;
                            break;
                        }
                    }
                    if (canRemove) {
                        List<User> users = library.getUsers();
                        for (Song song : album.getSongs()) {
                            for (User u : users) {
                                for (Playlist p : u.getPlaylists()) {
                                    for (Song songPlaylist : p.getSongs()) {
                                        if (song == songPlaylist) {
                                            canRemove = false;
                                            break;
                                        }
                                    }
                                    if (!canRemove) {
                                        break;
                                    }
                                }
                                if (!canRemove) {
                                    break;
                                }
                            }
                            if (!canRemove) {
                                break;
                            }
                        }
                    }
                }
                if (canRemove) {
                    message = getUsername() + " deleted the album successfully.";
                    artist.removeAlbum(album);
                } else {
                    message = getUsername() + " can't delete this album.";
                }
            }
        }
        return new RemoveAlbumCommandOutput(getUsername(), getTimestamp(), message);
    }
}
