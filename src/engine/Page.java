package engine;

import library.User;

public abstract class Page {
    private User owner;
    private int visitors;
    public abstract String getContents();

    public Page(final User owner) {
        this.owner = owner;
        this.visitors = 0;
    }

    public User getOwner() {
        return owner;
    }

    public int getVisitors() {
        return visitors;
    }

    public void visit() {
        visitors++;
    }

    public void unvisit() {
        visitors--;
    }
}
