package engine;

import library.Playlist;
import library.Song;
import library.User;

import java.util.ArrayList;

public final class HomePage extends Page {
    public static final int NUM_MATCHES = 5;

    public HomePage(final User owner) {
        super(owner);
    }

    @Override
    public String getContents() {
        ArrayList<Song> likedSongs = new ArrayList<>(getOwner().getLikedSongs());
        likedSongs.sort((s1, s2) -> Integer.compare(s2.getLikes(), s1.getLikes()));
        ArrayList<Playlist> followedPlaylists = new ArrayList<>(getOwner().getFollowedPlaylists());
        followedPlaylists.sort((p1, p2) -> {
            int numLikes1 = 0, numLikes2 = 0;
            for (Song song : p1.getSongs()) {
                numLikes1 += song.getLikes();
            }
            for (Song song : p2.getSongs()) {
                numLikes2 += song.getLikes();
            }
            return -Integer.compare(numLikes1, numLikes2);
        });
        StringBuilder value = new StringBuilder("Liked songs:\n\t[");
        if (!likedSongs.isEmpty()) {
            value.append(likedSongs.get(0).getName());
            for (int i = 1; i < Math.min(likedSongs.size(), NUM_MATCHES); i++) {
                value.append(", ").append(likedSongs.get(i).getName());
            }
        }
        value.append("]\n\nFollowed playlists:\n\t[");
        if (!followedPlaylists.isEmpty()) {
            value.append(followedPlaylists.get(0).getName());
            for (int i = 1; i < Math.min(followedPlaylists.size(), NUM_MATCHES); i++) {
                value.append(", ").append(followedPlaylists.get(i).getName());
            }
        }
        return value.append("]").toString();
    }
}
