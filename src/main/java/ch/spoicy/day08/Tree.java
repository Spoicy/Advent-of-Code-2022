package ch.spoicy.day08;

import ch.spoicy.day01.Elf;

/**
 * The {@code Tree} class represents a tree in a {@code ForestMap}.
 */
public final class Tree implements Comparable<Tree> {

    private final int height;
    private boolean visible = false;

    public Tree(final int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void makeVisible() {
        this.visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public String toString() {
        return Integer.toString(height);
    }

    @Override
    public int compareTo(Tree o) {
        return Integer.compare(o.height, this.height);
    }
}
