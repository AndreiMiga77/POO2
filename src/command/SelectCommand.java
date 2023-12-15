package command;

import java.util.List;

import command.output.CommandOutput;
import command.output.SelectCommandOutput;
import engine.Page;
import library.Library;
import library.Playable;
import library.User;

public final class SelectCommand extends Command {
    private Integer itemNumber;

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(final Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        List<Playable> lastSearch = user.getLastSearch();
        List<Page> lastSearchedPages = user.getLastSearchedPages();
        String message;
        if (user.isOffline()) {
            message = user.getUsername() + " is offline.";
        } else if (lastSearch == null && lastSearchedPages == null) {
            message = "Please conduct a search before making a selection.";
        } else if (lastSearch != null) {
            if (getItemNumber() > lastSearch.size()) {
                message = "The selected ID is too high.";
            } else {
                message = "Successfully selected " +  lastSearch.get(itemNumber - 1).getName() + ".";
                user.setSelectedSource(itemNumber - 1);
            }
        } else {
            if (getItemNumber() > lastSearchedPages.size()) {
                message = "The selected ID is too high.";
            } else {
                message = "Successfully selected " +
                        lastSearchedPages.get(itemNumber - 1).getOwner().getUsername() + "'s page.";
                user.setCurrentPage(lastSearchedPages.get(itemNumber - 1));
            }
        }
        return new SelectCommandOutput(getUsername(), getTimestamp(), message);
    }
}
