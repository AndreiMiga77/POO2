package command;

import command.output.CommandOutput;
import command.output.RemoveEventCommandOutput;
import library.Library;
import library.User;
import library.Artist;
import library.Event;

import java.util.List;

public final class RemoveEventCommand extends Command {
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
            message = user.getUsername() + " is not an artist.";
        } else {
            Artist artist = (Artist) user;
            List<Event> events = artist.getEvents();
            Event event = events
                    .stream()
                    .filter(a -> a.getName().equals(name))
                    .findAny()
                    .orElse(null);
            if (event != null) {
                artist.removeEvent(event);
                message = artist.getUsername() + " deleted the event successfully.";
            } else {
                message = artist.getUsername() + " has no event with the given name.";

            }
        }
        return new RemoveEventCommandOutput(getUsername(), getTimestamp(), message);
    }
}
