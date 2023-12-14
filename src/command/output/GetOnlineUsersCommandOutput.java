package command.output;

import java.util.ArrayList;
import java.util.List;

public final class GetOnlineUsersCommandOutput extends CommandOutput {
    private Integer timestamp;
    private ArrayList<String> result;

    public GetOnlineUsersCommandOutput(final Integer timestamp, final List<String> usernames) {
        this.timestamp = timestamp;
        result = new ArrayList<>(usernames);
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public ArrayList<String> getResult() {
        return result;
    }
}
