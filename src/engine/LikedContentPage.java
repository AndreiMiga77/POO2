package engine;

import library.Playlist;
import library.Song;
import library.User;

import java.util.ArrayList;

public final class LikedContentPage extends Page {
    public LikedContentPage(final User owner) {
        super(owner);
    }

    @Override
    public String getContents() {
        ArrayList<Song> likedSongs = new ArrayList<>(getOwner().getLikedSongs());
        ArrayList<Playlist> followedPlaylists = new ArrayList<>(getOwner().getFollowedPlaylists());
        StringBuilder value = new StringBuilder("Liked songs:\n\t[");
        if (!likedSongs.isEmpty()) {
            value.append(likedSongs.get(0).getName())
                    .append(" - ")
                    .append(likedSongs.get(0).getArtist());
            for (int i = 1; i < likedSongs.size(); i++) {
                value.append(", ").append(likedSongs.get(i).getName());
            }
        }
        value.append("]\n\nFollowed playlists:\n\t[");
        if (!followedPlaylists.isEmpty()) {
            value.append(followedPlaylists.get(0).getName())
                    .append(" - ")
                    .append(followedPlaylists.get(0).getOwner().getUsername());
            for (int i = 1; i < followedPlaylists.size(); i++) {
                value.append(",")
                        .append(followedPlaylists.get(i).getName())
                        .append(" - ")
                        .append(followedPlaylists.get(i).getOwner().getUsername());
            }
        }
        return value.append("]").toString();
    }
}
