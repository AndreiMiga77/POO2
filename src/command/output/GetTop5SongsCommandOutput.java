package command.output;

import java.util.ArrayList;
import java.util.List;

public final class GetTop5SongsCommandOutput extends CommandOutput {
    private final String command = "getTop5Songs";
    private Integer timestamp;
    private ArrayList<String> result;

    public GetTop5SongsCommandOutput(final Integer timestamp, final List<String> songs) {
        this.timestamp = timestamp;
        this.result = new ArrayList<>(songs);
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
