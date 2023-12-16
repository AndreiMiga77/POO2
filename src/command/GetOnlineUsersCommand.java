package command;

import command.output.CommandOutput;
import command.output.GetOnlineUsersCommandOutput;
import library.Library;
import library.User;

import java.util.ArrayList;

public final class GetOnlineUsersCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        ArrayList<User> userList = library.getRegularUsers();
        ArrayList<String> onlineUsers = new ArrayList<>(userList.size());
        for (User u : userList) {
            if (!u.isOffline()) {
                onlineUsers.add(u.getUsername());
            }
        }
        return new GetOnlineUsersCommandOutput(getTimestamp(), onlineUsers);
    }
}
