package engine;

import library.User;

public abstract class Page {
    private User owner;
    public abstract String getContents();

    public Page(final User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }
}
