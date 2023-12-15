package command.output;

public final class ShuffleCommandOutput extends CommandOutput {
    private final String command = "shuffle";
    private String user;
    private Integer timestamp;
    private String message;
    public ShuffleCommandOutput(final String user, final Integer timestamp, final String message) {
        this.user = user;
        this.timestamp = timestamp;
        this.message = message;
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
