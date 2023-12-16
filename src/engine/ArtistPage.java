package engine;

import library.Artist;

import java.time.format.DateTimeFormatter;

public final class ArtistPage extends Page {
    public ArtistPage(final Artist user) {
        super(user);
    }

    @Override
    public String getContents() {
        StringBuilder value = new StringBuilder("Albums:\n\t[");
        Artist owner = (Artist) getOwner();
        if (!owner.getAlbums().isEmpty()) {
            value.append(owner.getAlbums().get(0).getName());
            for (int i = 1; i < owner.getAlbums().size(); i++) {
                value.append(", ").append(owner.getAlbums().get(i).getName());
            }
        }
        value.append("]\n\nMerch:\n\t[");
        if (!owner.getMerchItems().isEmpty()) {
            value.append(owner.getMerchItems().get(0).getName())
                    .append(" - ")
                    .append(owner.getMerchItems().get(0).getPrice())
                    .append(":\n\t")
                    .append(owner.getMerchItems().get(0).getDescription());
            for (int i = 1; i < owner.getMerchItems().size(); i++) {
                value.append(", ")
                        .append(owner.getMerchItems().get(i).getName())
                        .append(" - ")
                        .append(owner.getMerchItems().get(i).getPrice())
                        .append(":\n\t")
                        .append(owner.getMerchItems().get(i).getDescription());
            }
        }
        value.append("]\n\nEvents:\n\t[");
        if (!owner.getEvents().isEmpty()) {
            value.append(owner.getEvents().get(0).getName())
                    .append(" - ")
                    .append(owner.getEvents().get(0)
                            .getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                    .append(":\n\t")
                    .append(owner.getEvents().get(0).getDescription());
            for (int i = 1; i < owner.getEvents().size(); i++) {
                value.append(", ")
                        .append(owner.getEvents().get(i).getName())
                        .append(" - ")
                        .append(owner.getEvents().get(i)
                                .getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                        .append(":\n\t")
                        .append(owner.getEvents().get(i).getDescription());
            }
        }

        return value.append("]").toString();
    }
}
