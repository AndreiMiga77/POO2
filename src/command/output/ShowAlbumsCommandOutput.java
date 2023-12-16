package command.output;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class ShowAlbumsCommandOutput extends CommandOutput {
    private final String command = "showAlbums";
    private String user;
    private Integer timestamp;
    private ArrayList<LinkedHashMap<String, Object>> result;
    public ShowAlbumsCommandOutput(final String u,
                                   final Integer t,
                                   final ArrayList<LinkedHashMap<String, Object>> r) {
        this.user = u;
        this.timestamp = t;
        this.result = r;
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
}
