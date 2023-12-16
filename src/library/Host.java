package library;

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

    /**
     * Add a new podcast, owned by this host
     * @param podcast podcast to add
     */
    public void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * Remove a host's podcast
     * @param podcast podcast to remove
     */
    public void removePodcast(final Podcast podcast) {
        podcasts.remove(podcast);
        podcast.delete();
    }

    public List<Podcast> getPodcasts() {
        return Collections.unmodifiableList(podcasts);
    }

    /**
     * Add an announcement to the host's page
     * @param announcement announcement to add
     */
    public void addAnnouncement(final Announcement announcement) {
        announcements.add(announcement);
    }

    /**
     * Remove announcement from the host's page
     * @param announcement announcement to remove
     */
    public void removeAnnouncement(final Announcement announcement) {
        announcements.remove(announcement);
    }

    public List<Announcement> getAnnouncements() {
        return Collections.unmodifiableList(announcements);
    }
}
