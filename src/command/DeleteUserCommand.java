package command;

import command.output.CommandOutput;
import command.output.DeleteUserCommandOutput;
import engine.Page;
import library.Library;
import library.User;
import library.Artist;
import library.Album;
import library.Song;
import library.Host;
import library.Podcast;
import library.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class DeleteUserCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        String message = null;
        if (user == null) {
            message = "The username " + getUsername() + " doesn't exist.";
        } else if (user.getHomePage().getVisitors() > 0) {
            message = user.getUsername() + " can't be deleted.";
        } else {
            ArrayList<User> users = library.getRegularUsers();
            for (User u : users) {
                List<Page> pages = u.getLastSearchedPages();
                if (pages != null) {
                    for (Page p : pages) {
                        if (p.getOwner() == user) {
                            message = user.getUsername() + " can't be deleted.";
                            break;
                        }
                    }
                }
                if (message != null) {
                    break;
                }
            }
            if (message == null) {
                if (user.getType() == User.UserType.USER) {
//                    for (User u : library.getUsers()) {
//                        for (Playlist p : u.getPlaylists()) {
//                            if (p.getOwner() == user && p.getNumListeners() > 0) {
//                                if (p.getNumListeners() == 1 &&
//                                        user.getPlayer().getCurrent() == p) {
//                                    continue;
//                                }
//                                message = user.getUsername() + " can't be deleted.";
//                                break;
//                            }
//                        }
//                        if (message != null) {
//                            break;
//                        }
//                    }
                    if (message == null) {
                        library.deleteUser(user);
                        message = user.getUsername() + " was successfully deleted.";
                    }
                } else if (user.getType() == User.UserType.ARTIST) {
                    Artist artist = (Artist) user;
                    for (Album album : artist.getAlbums()) {
                        if (album.getNumListeners() != 0) {
                            message = user.getUsername() + " can't be deleted.";
                            break;
                        }
                        for (Song song : album.getSongs()) {
                            if (song.getNumListeners() != 0) {
                                message = user.getUsername() + " can't be deleted.";
                                break;
                            }
                        }
                        if (message != null) {
                            break;
                        }
                    }
                    for (User u : library.getUsers()) {
                        for (Playlist p : u.getPlaylists()) {
                            Stream<Song> str = p.getSongs().stream();
                            if (p.getNumListeners() > 0
                                    && str.anyMatch(s -> artist.getAllSongs().contains(s))) {
                                if (p.getNumListeners() == 1
                                        && artist.getPlayer().getCurrent() == p) {
                                    continue;
                                }
                                message = user.getUsername() + " can't be deleted.";
                                break;
                            }
                        }
                        if (message != null) {
                            break;
                        }
                    }
                    if (message == null) {
                        library.deleteUser(user);
                        message = user.getUsername() + " was successfully deleted.";
                    }
                } else if (user.getType() == User.UserType.HOST) {
                    Host host = (Host) user;
                    for (Podcast podcast : host.getPodcasts()) {
                        if (podcast.getNumListeners() != 0) {
                            message = user.getUsername() + " can't be deleted.";
                            break;
                        }
                    }
                    if (message == null) {
                        library.deleteUser(user);
                        message = user.getUsername() + " was successfully deleted.";
                    }
                }
            }
        }
        return new DeleteUserCommandOutput(getUsername(), getTimestamp(), message);
    }
}
