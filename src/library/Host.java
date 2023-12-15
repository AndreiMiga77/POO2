package library;

import engine.ArtistPage;
import engine.HostPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Host extends User {
    private ArrayList<Podcast> podcasts;
    private ArrayList<Announcement> announcements;

    public Host(final String username, final int age, final String city) {
        super(username, age, city);
        setType(UserType.HOST);
        podcasts = new ArrayList<>();
        announcements = new ArrayList<>();
        homePage = new HostPage(this);
        currentPage = homePage;
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

    public void addAnnouncement(Announcement announcement) {
        announcements.add(announcement);
    }

    public void removeAnnouncement(Announcement announcement) {
        announcements.remove(announcement);
    }

    public List<Announcement> getAnnouncements() {
        return Collections.unmodifiableList(announcements);
    }
}
