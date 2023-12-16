package command.output;

public final class DeleteUserCommandOutput extends CommandOutput {
    private final String command = "deleteUser";
    private String user;
    private Integer timestamp;
    private String message;

    public DeleteUserCommandOutput(final String user,
                                   final Integer timestamp,
                                   final String message) {
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

    public String getMessage() {
        return message;
    }
}
