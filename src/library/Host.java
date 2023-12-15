package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Host extends User {
    private ArrayList<Podcast> podcasts;

    public Host(final String username, final int age, final String city) {
        super(username, age, city);
        setType(UserType.HOST);
        podcasts = new ArrayList<>();
    }

    public void addPodcast(Podcast podcast) {
        podcasts.add(podcast);
    }

    public void removePodcast(Podcast podcast) {
        podcasts.remove(podcast);
    }

    public List<Podcast> getPodcasts() {
        return Collections.unmodifiableList(podcasts);
    }
}
