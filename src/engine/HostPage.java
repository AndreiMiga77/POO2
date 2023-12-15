package engine;

import library.Artist;
import library.Host;
import library.Podcast;
import library.PodcastEpisode;

import java.time.format.DateTimeFormatter;
import java.util.List;

public final class HostPage extends Page {
    public HostPage(Host user) {
        super(user);
    }

    @Override
    public String getContents() {
        StringBuilder value = new StringBuilder("Podcasts:\n\t[");
        Host owner = (Host) getOwner();
        List<Podcast> podcasts = owner.getPodcasts();
        for (int i = 0; i < podcasts.size(); i++) {
            Podcast p = podcasts.get(i);
            if (i > 0) {
                value.append(", ");
            }
            value.append(p.getName())
                    .append(":\n\t[");
            for (int j = 0; j < p.getEpisodes().size(); j++) {
                PodcastEpisode curEpisode = podcasts.get(i).getEpisodes().get(j);
                if (j > 0) {
                    value.append(", ");
                }
                value.append(curEpisode.getName()).append(" - ").append(curEpisode.getDescription());
            }
            value.append("]\n");
        }
        value.append("]\n\nAnnouncements:\n\t[");
        for (int i = 0; i < owner.getAnnouncements().size(); i++) {
            value.append(owner.getAnnouncements().get(i).getName())
                    .append(":\n\t")
                    .append(owner.getAnnouncements().get(i).getDescription())
                    .append("\n");
        }
        return value.append("]").toString();
    }
}
