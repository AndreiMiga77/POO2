package command;

import command.output.CommandOutput;
import command.output.ShowAlbumsCommandOutput;
import library.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class ShowAlbumsCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        if (user == null || user.getType() != User.UserType.ARTIST) {
            return null;
        } else {
            Artist artist = (Artist) user;
            ArrayList<LinkedHashMap<String, Object>> albumData = new ArrayList<>();
            for (Album album : artist.getAlbums()) {
                LinkedHashMap<String, Object> albumHashMap = new LinkedHashMap<>();
                albumHashMap.put("name", album.getName());
                ArrayList<String> songNames = new ArrayList<>();
                for (Song song : album.getSongs()) {
                    songNames.add(song.getName());
                }
                albumHashMap.put("songs", songNames);
                albumData.add(albumHashMap);
            }
            return new ShowAlbumsCommandOutput(getUsername(), getTimestamp(), albumData);
        }
    }
}
