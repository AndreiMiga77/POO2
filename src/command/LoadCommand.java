package command;

import command.output.CommandOutput;
import command.output.LoadCommandOutput;
import library.Library;
import library.Playable;
import library.User;

import java.util.List;

public final class LoadCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        List<Playable> lastSearch = user.getLastSearch();
        String message;
        if (user.isOffline()) {
            message = user.getUsername() + " is offline.";
        } else if (lastSearch == null || user.getSelectedSource() < 0) {
            message = "Please select a source before attempting to load.";
        } else {
            user.getPlayer().load(lastSearch.get(user.getSelectedSource()));
            user.setSelectedSource(-1);
            user.setLastSearch(null);
            message = "Playback loaded successfully.";
        }
        return new LoadCommandOutput(getUsername(), getTimestamp(), message);
    }
}
