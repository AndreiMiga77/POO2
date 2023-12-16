package command;

import command.output.CommandOutput;
import command.output.RemoveAnnouncementCommandOutput;
import library.Announcement;
import library.Host;
import library.Library;
import library.User;

import java.util.List;

public final class RemoveAnnouncementCommand extends Command {
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
        } else if (user.getType() != User.UserType.HOST) {
            message = user.getUsername() + " is not a host.";
        } else {
            Host host = (Host) user;
            List<Announcement> announcements = host.getAnnouncements();
            Announcement ann = announcements
                    .stream()
                    .filter(a -> a.getName().equals(name))
                    .findAny()
                    .orElse(null);
            if (ann != null) {
                host.removeAnnouncement(ann);
                message = host.getUsername() + " has successfully deleted the announcement.";
            } else {
                message = host.getUsername() + " has no announcement with the given name.";

            }
        }
        return new RemoveAnnouncementCommandOutput(getUsername(), getTimestamp(), message);
    }
}
