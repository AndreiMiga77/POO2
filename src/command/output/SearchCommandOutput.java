package command.output;

import java.util.List;
import java.util.ArrayList;

public final class SearchCommandOutput extends CommandOutput {
    private String user;
    private Integer timestamp;
    private String message;
    private ArrayList<String> results;
    public SearchCommandOutput(final String user,
                               final Integer timestamp,
                               final String message,
                               final List<String> results) {
        this.user = user;
        this.timestamp = timestamp;
        this.message = message;
        this.results = new ArrayList<>(results);
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

    public ArrayList<String> getResults() {
        return results;
    }

    public void setResults(final ArrayList<String> res) {
        results = res;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
