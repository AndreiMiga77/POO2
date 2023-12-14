package command;

import command.output.CommandOutput;
import command.output.GetOnlineUsersCommandOutput;
import library.Library;
import library.User;

import java.util.ArrayList;
import java.util.List;

public final class GetOnlineUsersCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        List<User> userList = library.getUsers();
        ArrayList<String> onlineUsers = new ArrayList<>(userList.size());
        for (User u : userList) {
            if (!u.isOffline())
                onlineUsers.add(u.getUsername());
        }
        return new GetOnlineUsersCommandOutput(getTimestamp(), onlineUsers);
    }
}
