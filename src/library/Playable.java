package library;

public interface Playable {
    /**
     * Name
     */
    String getName();

    /**
     * Duration
     */
    int getDuration();

    /**
     * Number of tracks the playable contains
     */
    int getNumTracks();

    /**
     * Get a track
     * @param i zero-based index
     */
    Playable getTrack(int i);

    /**
     * Whether the playable allows liking
     */
    boolean allowsLike();

    /**
     * Whether the playable allows following
     */
    boolean allowsFollow();

    /**
     * Whether the playable allows shuffling
     */
    boolean allowsShuffling();

    /**
     * Whether players remember the seek position when the playable is unloaded
     */
    boolean remembersTimestamp();

    /**
     * Whether the playable allows tracks to be individually repeated
     */
    boolean isPlaylistRepeatable();

    /**
     * Whether the playable allows seeking by arbitrary amounts of time
     * Does not affect skipping or rewinding
     */
    boolean isSeekable();

    /**
     * Number of users listening to the playable
     */
    int getNumListeners();

    /**
     * Add one listener
     */
    void listen();

    /**
     * Remove one listener
     */
    void stopListening();
}
