package command;

import command.output.AddEventCommandOutput;
import command.output.CommandOutput;
import library.Artist;
import library.Event;
import library.Library;
import library.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class AddEventCommand extends Command {
    private String name;
    private String description;
    private String date;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
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
            if (events.stream().anyMatch(event -> event.getName().equals(name))) {
                message = artist.getUsername() + " has another event with the same name.";
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                try {
                    LocalDate localDate = LocalDate.parse(date, formatter);
                    artist.addEvent(new Event(name, description, localDate));
                    message = artist.getUsername() + " has added new event successfully.";
                } catch (Exception e) {
                    message = "Event for " + artist.getUsername() +  " does not have a valid date.";
                }
            }
        }
        return new AddEventCommandOutput(getUsername(), getTimestamp(), message);
    }
}
