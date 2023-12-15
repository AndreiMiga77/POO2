package command;

import command.output.CommandOutput;
import command.output.GetAllUsersCommandOutput;
import library.Artist;
import library.Host;
import library.Library;
import library.User;

import java.util.ArrayList;

public final class GetAllUsersCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        ArrayList<String> userNames = new ArrayList<>();
        for (User u : library.getRegularUsers()) {
            userNames.add(u.getUsername());
        }
        for (Artist a : library.getArtists()) {
            userNames.add(a.getUsername());
        }
        for (Host h : library.getHosts()) {
            userNames.add(h.getUsername());
        }
        return new GetAllUsersCommandOutput(getTimestamp(), userNames);
    }
}
