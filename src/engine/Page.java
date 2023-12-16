package engine;

import library.User;

public abstract class Page {
    private User owner;
    private int visitors;

    /**
     * Get contents of the page as string
     */
    public abstract String getContents();

    public Page(final User owner) {
        this.owner = owner;
        this.visitors = 0;
    }

    public final User getOwner() {
        return owner;
    }

    public final int getVisitors() {
        return visitors;
    }

    /**
     * Add a new visitor to the page
     */
    public final void visit() {
        visitors++;
    }

    /**
     * Remove a visitor from the page
     */
    public final void unvisit() {
        visitors--;
    }
}
