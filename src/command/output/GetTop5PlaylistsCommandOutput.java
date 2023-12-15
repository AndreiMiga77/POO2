package command.output;

import java.util.ArrayList;
import java.util.List;

public final class GetTop5PlaylistsCommandOutput extends CommandOutput {
    private final String command = "getTop5Playlists";
    private Integer timestamp;
    private ArrayList<String> result;

    public GetTop5PlaylistsCommandOutput(final Integer timestamp, final List<String> playlists) {
        this.timestamp = timestamp;
        this.result = new ArrayList<>(playlists);
    }

    public String getCommand() {
        return command;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Integer timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(final ArrayList<String> result) {
        this.result = result;
    }
}
