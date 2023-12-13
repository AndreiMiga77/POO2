package command;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import command.output.CommandOutput;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command"
)
@JsonSubTypes({
        @Type(value = SearchCommand.class, name = "search"),
        @Type(value = SelectCommand.class, name = "select"),
        @Type(value = LoadCommand.class, name = "load"),
        @Type(value = PlayPauseCommand.class, name = "playPause"),
        @Type(value = RepeatCommand.class, name = "repeat"),
        @Type(value = ShuffleCommand.class, name = "shuffle"),
        @Type(value = ForwardCommand.class, name = "forward"),
        @Type(value = BackwardCommand.class, name = "backward"),
        @Type(value = LikeCommand.class, name = "like"),
        @Type(value = NextCommand.class, name = "next"),
        @Type(value = PrevCommand.class, name = "prev"),
        @Type(value = AddRemoveInPlaylistCommand.class, name = "addRemoveInPlaylist"),
        @Type(value = StatusCommand.class, name = "status"),
        @Type(value = CreatePlaylistCommand.class, name = "createPlaylist"),
        @Type(value = SwitchVisibilityCommand.class, name = "switchVisibility"),
        @Type(value = FollowCommand.class, name = "follow"),
        @Type(value = ShowPlaylistsCommand.class, name = "showPlaylists"),
        @Type(value = ShowPreferredSongsCommand.class, name = "showPreferredSongs"),
        @Type(value = GetTop5SongsCommand.class, name = "getTop5Songs"),
        @Type(value = GetTop5PlaylistsCommand.class, name = "getTop5Playlists"),
})
public abstract class Command {
    private String username;
    private Integer timestamp;

    public final String getUsername() {
        return username;
    }

    public final void setUsername(final String username) {
        this.username = username;
    }

    public final Integer getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(final Integer timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Execute the command
     * @return command's output (suitable for processing by ObjectMapper)
     */
    public abstract CommandOutput execute();
}
