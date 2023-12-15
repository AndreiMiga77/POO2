package command.output;

public final class CreatePlaylistCommandOutput extends CommandOutput {
    private final String command = "createPlaylist";
    private String user;
    private Integer timestamp;
    private String message;
    public CreatePlaylistCommandOutput(final String u, final Integer t, final String m) {
        this.user = u;
        this.timestamp = t;
        this.message = m;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
