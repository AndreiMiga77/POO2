package command.output;

public final class PlayPauseCommandOutput extends CommandOutput {
    private String user;
    private Integer timestamp;
    private String message;
    public PlayPauseCommandOutput(final String usr, final Integer timestamp, final String message) {
        this.user = usr;
        this.timestamp = timestamp;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
