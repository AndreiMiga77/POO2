package command;

import command.output.AddEventCommandOutput;
import command.output.AddMerchCommandOutput;
import command.output.CommandOutput;
import library.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddMerchCommand extends Command {
    private String name;
    private String description;
    private Integer price;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
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
            List<Merch> merch = artist.getMerchItems();
            if (merch.stream().anyMatch(m -> m.getName().equals(name))) {
                message = artist.getUsername() + " has merchandise with the same name.";
            } else if (price < 0) {
                message = "Price for merchandise can not be negative.";
            } else {
                artist.addMerch(new Merch(name, description, price));
                message = artist.getUsername() + " has added new merchandise successfully.";
            }
        }
        return new AddMerchCommandOutput(getUsername(), getTimestamp(), message);
    }
}
