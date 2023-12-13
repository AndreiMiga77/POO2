package command.output;

import java.util.HashMap;
import java.util.LinkedHashMap;

public final class StatusCommandOutput extends CommandOutput {
    private String user;
    private Integer timestamp;
    private LinkedHashMap<String, Object> stats;
    public StatusCommandOutput(final String user,
                               final Integer timestamp,
                               final LinkedHashMap<String, Object> stats) {
        this.user = user;
        this.timestamp = timestamp;
        this.stats = stats;
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

    public HashMap<String, Object> getStats() {
        return stats;
    }

    public void setStats(final LinkedHashMap<String, Object> stats) {
        this.stats = stats;
    }
}
