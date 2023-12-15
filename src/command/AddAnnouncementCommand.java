package command;

import command.output.AddAnnouncementCommandOutput;
import command.output.CommandOutput;
import library.Library;
import library.User;
import library.Host;
import library.Announcement;

import java.util.List;

public class AddAnnouncementCommand extends Command {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        String message = null;
        if (user == null) {
            message = "The username " + getUsername() + " doesn't exist.";
        } else if (user.getType() != User.UserType.HOST) {
            message = user.getUsername() + " is not a host.";
        } else {
            Host host = (Host) user;
            List<Announcement> announcements = host.getAnnouncements();
            if (announcements.stream().anyMatch(ann -> ann.getName().equals(name))) {
                message = host.getUsername() + " has already added an announcement with this name.";
            } else {
                host.addAnnouncement(new Announcement(name, description));
                message = host.getUsername() + " has successfully added new announcement.";
            }
        }
        return new AddAnnouncementCommandOutput(getUsername(), getTimestamp(), message);
    }
}
