package command.output;

import java.util.ArrayList;
import java.util.List;

public final class ShowPreferredSongsCommandOutput extends CommandOutput {
    private String user;
    private Integer timestamp;
    private ArrayList<String> result;

    public ShowPreferredSongsCommandOutput(final String user,
                                           final Integer timestamp,
                                           final List<String> songs) {
        this.user = user;
        this.timestamp = timestamp;
        this.result = new ArrayList<>(songs);
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

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(final ArrayList<String> res) {
        result = res;
    }
}
