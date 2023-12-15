package command.output;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class ShowPlaylistsCommandOutput extends CommandOutput {
    private final String command = "showPlaylists";
    private String user;
    private Integer timestamp;
    private ArrayList<LinkedHashMap<String, Object>> result;

    public ShowPlaylistsCommandOutput(final String user,
                                      final Integer timestamp,
                                      final ArrayList<LinkedHashMap<String, Object>> data) {
        this.user = user;
        this.timestamp = timestamp;
        this.result = data;
    }

    public String getCommand() {
        return command;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Integer timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<LinkedHashMap<String, Object>> getResult() {
        return result;
    }

    public void setResult(final ArrayList<LinkedHashMap<String, Object>> res) {
        result = res;
    }
}
