package engine;

import library.Playable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public final class Player {
    public enum RepeatState {
        NO_REPEAT,
        REPEAT_ALL,
        REPEAT_CURRENT,
        REPEAT_ONCE,
    }

    private Playable current;
    private int currentTrackIndex;
    private int currentTime;
    private boolean paused;
    private boolean shuffled;
    private HashMap<Playable, Integer> timestampsRemembered;
    private RepeatState repeatState;
    private ArrayList<Integer> trackIndexList;

    public Player() {
        paused = true;
        repeatState = RepeatState.NO_REPEAT;
        timestampsRemembered = new HashMap<>();
    }

    /**
     * Advance the time for the audio player
     * @param difTime number of seconds to advance time
     */
    public void tickTime(final int difTime) {
        int dif = difTime;
        if (!isLoaded()) {
            return;
        }
        if (isPaused()) {
            return;
        }
        if (current.getNumTracks() <= 0) {
            return;
        }
        int newTime = currentTime + dif;
        int currentTrackId = trackIndexList.get(currentTrackIndex);
        if (currentTime + dif < current.getTrack(currentTrackId).getDuration()) {
            currentTime += dif;
        } else {
            dif -= current.getTrack(currentTrackId).getDuration() - currentTime;
            if (repeatState == RepeatState.REPEAT_CURRENT) {
                currentTime = 0;
                tickTime(dif);
                return;
            }
            for (int i = currentTrackIndex + 1; i < current.getNumTracks(); i++) {
                int trackId = trackIndexList.get(i);
                if (dif < current.getTrack(trackId).getDuration()) {
                    currentTime = dif;
                    currentTrackIndex = i;
                    return;
                }
                dif -= current.getTrack(i).getDuration();
            }
            if (repeatState == RepeatState.NO_REPEAT) {
                unload();
            } else if (repeatState == RepeatState.REPEAT_ALL) {
                currentTime = 0;
                currentTrackIndex = 0;
                if (dif > 0) {
                    tickTime(dif);
                }
            } else if (repeatState == RepeatState.REPEAT_ONCE) {
                currentTime = 0;
                currentTrackIndex = 0;
                repeatState = RepeatState.NO_REPEAT;
                if (dif > 0) {
                    tickTime(dif);
                }
            }
        }
    }

    /**
     * Whether there's any source loaded in the music player
     */
    public boolean isLoaded() {
        return current != null;
    }

    /**
     * Load a source and start playback
     * @param track source to load
     */
    public void load(final Playable track) {
        if (isLoaded()) {
            unload();
        }
        shuffled = false;
        paused = false;
        current = track;
        trackIndexList = new ArrayList<>(track.getNumTracks());
        for (int i = 0; i < track.getNumTracks(); i++) {
            trackIndexList.add(i);
        }
        if (track.remembersTimestamp()) {
            if (timestampsRemembered.containsKey(track)) {
                int rememberedTime = timestampsRemembered.get(track);
                int totalTime = 0;
                for (int i = 0; i < track.getNumTracks(); i++) {
                    if (rememberedTime < totalTime + track.getTrack(i).getDuration()) {
                        currentTime = rememberedTime - totalTime;
                        currentTrackIndex = i;
                        return;
                    }
                    totalTime += track.getTrack(i).getDuration();
                }
            }
        }
        currentTime = 0;
        currentTrackIndex = 0;
    }

    /**
     * Unloads the current source (if any) and pauses playback
     */
    public void unload() {
        if (current == null) {
            return;
        }
        int currentTrackId = trackIndexList.get(currentTrackIndex);
        if (current.remembersTimestamp()) {
            int prevTime = 0;
            for (int i = 0; i < currentTrackId; i++) {
                prevTime += current.getTrack(i).getDuration();
            }
            timestampsRemembered.put(current, prevTime + currentTime);
        }
        current = null;
        paused = true;
        shuffled = false;
        currentTime = 0;
        repeatState = RepeatState.NO_REPEAT;
    }

    /**
     * Whether the music player is paused
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Unpause the music player
     */
    public void resume() {
        paused = false;
    }

    /**
     * Pause the music player
     */
    public void pause() {
        paused = true;
    }

    /**
     * Skip the current track
     */
    public void skip() {
        paused = false;
        tickTime(getTimeRemaining());
    }

    /**
     * Rewind the current track to the beginning
     * If already at the beginning, go back one track
     */
    public void rewind() {
        paused = false;
        if (currentTime == 0 && currentTrackIndex > 0) {
            currentTrackIndex--;
        }
        currentTime = 0;
    }

    /**
     * Seek forward
     * @param time number of seconds to seek
     */
    public void seekForward(final int time) {
        if (getTimeRemaining() < time) {
            skip();
        } else {
            currentTime += time;
        }
    }

    /**
     * Seek backward
     * @param time number of seconds to seek
     */
    public void seekBackward(final int time) {
        if (currentTime <= time) {
            currentTime = 0;
        } else {
            currentTime -= time;
        }
    }

    /**
     * Whether the tracks are shuffled
     */
    public boolean isShuffled() {
        return shuffled;
    }

    /**
     * Shuffle the tracks
     * @param seed for the shuffling algorithm
     */
    public void shuffle(final long seed) {
        Collections.shuffle(trackIndexList, new Random(seed));
        for (int i = 0; i < trackIndexList.size(); i++) {
            if (trackIndexList.get(i) == currentTrackIndex) {
                currentTrackIndex = i;
                break;
            }
        }
        shuffled = true;
    }

    /**
     * Unshuffle the tracks (return to playlist order)
     */
    public void unshuffle() {
        currentTrackIndex = trackIndexList.get(currentTrackIndex);
        for (int i = 0; i < trackIndexList.size(); i++) {
            trackIndexList.set(i, i);
        }
        shuffled = false;
    }

    /**
     * Currently loaded track
     */
    public Playable getCurrentTrack() {
        if (current == null) {
            return null;
        }
        if (current.getNumTracks() <= 0) {
            return null;
        }
        if (currentTrackIndex < 0) {
            return null;
        }
        return current.getTrack(trackIndexList.get(currentTrackIndex));
    }

    /**
     * Currently loaded source
     */
    public Playable getCurrent() {
        return current;
    }

    /**
     * Time left for the current track
     */
    public int getTimeRemaining() {
        if (current == null) {
            return 0;
        }
        if (current.getNumTracks() <= 0) {
            return 0;
        }
        if (currentTrackIndex < 0) {
            return 0;
        }
        int currentTrackId = trackIndexList.get(currentTrackIndex);
        return current.getTrack(currentTrackId).getDuration() - currentTime;
    }

    public RepeatState getRepeatState() {
        return repeatState;
    }

    public void setRepeatState(final RepeatState repeatState) {
        this.repeatState = repeatState;
    }
}
