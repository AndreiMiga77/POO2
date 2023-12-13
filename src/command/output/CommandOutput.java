package command.output;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command"
)
@JsonSubTypes({
        @Type(value = SearchCommandOutput.class, name = "search"),
        @Type(value = SelectCommandOutput.class, name = "select"),
        @Type(value = LoadCommandOutput.class, name = "load"),
        @Type(value = PlayPauseCommandOutput.class, name = "playPause"),
        @Type(value = RepeatCommandOutput.class, name = "repeat"),
        @Type(value = ShuffleCommandOutput.class, name = "shuffle"),
        @Type(value = ForwardCommandOutput.class, name = "forward"),
        @Type(value = BackwardCommandOutput.class, name = "backward"),
        @Type(value = LikeCommandOutput.class, name = "like"),
        @Type(value = NextCommandOutput.class, name = "next"),
        @Type(value = PrevCommandOutput.class, name = "prev"),
        @Type(value = AddRemoveInPlaylistCommandOutput.class, name = "addRemoveInPlaylist"),
        @Type(value = StatusCommandOutput.class, name = "status"),
        @Type(value = CreatePlaylistCommandOutput.class, name = "createPlaylist"),
        @Type(value = SwitchVisibilityCommandOutput.class, name = "switchVisibility"),
        @Type(value = FollowCommandOutput.class, name = "follow"),
        @Type(value = ShowPlaylistsCommandOutput.class, name = "showPlaylists"),
        @Type(value = ShowPreferredSongsCommandOutput.class, name = "showPreferredSongs"),
        @Type(value = GetTop5SongsCommandOutput.class, name = "getTop5Songs"),
        @Type(value = GetTop5PlaylistsCommandOutput.class, name = "getTop5Playlists"),
})
public abstract class CommandOutput {
}
