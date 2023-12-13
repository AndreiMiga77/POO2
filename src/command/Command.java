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
        @Type(value = ChangePageCommand.class, name = "changePage"),
        @Type(value = PrintCurrentPageCommand.class, name = "printCurrentPage"),
        @Type(value = AddUserCommand.class, name = "addUser"),
        @Type(value = DeleteUserCommand.class, name = "deleteUser"),
        @Type(value = ShowAlbumsCommand.class, name = "showAlbums"),
        @Type(value = ShowPodcastsCommand.class, name = "showPodcasts"),
        @Type(value = AddAlbumCommand.class, name = "addAlbum"),
        @Type(value = RemoveAlbumCommand.class, name = "removeAlbum"),
        @Type(value = AddEventCommand.class, name = "addEvent"),
        @Type(value = RemoveEventCommand.class, name = "removeEvent"),
        @Type(value = AddMerchCommand.class, name = "addMerch"),
        @Type(value = AddPodcastCommand.class, name = "addPodcast"),
        @Type(value = RemovePodcastCommand.class, name = "removePodcast"),
        @Type(value = AddAnnouncementCommand.class, name = "addAnnouncement"),
        @Type(value = RemoveAnnouncementCommand.class, name = "removeAnnouncement"),
        @Type(value = SwitchConnectionStatusCommand.class, name = "switchConnectionStatus"),
        @Type(value = GetTop5AlbumsCommand.class, name = "getTop5Albums"),
        @Type(value = GetTop5ArtistsCommand.class, name = "getTop5Artists"),
        @Type(value = GetAllUsersCommand.class, name = "getAllUsers"),
        @Type(value = GetOnlineUsersCommand.class, name = "getOnlineUsers"),
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
