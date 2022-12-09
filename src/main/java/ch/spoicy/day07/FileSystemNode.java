package ch.spoicy.day07;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FileSystemNode {

    private FileSystemNode parentNode;
    private List<FileSystemNode> directoryFiles;
    // false for directory, true for file
    private boolean flag;
    private int size;
    private String name;
    private List<NodeEventListener> eventListeners = new ArrayList<>();
    private static final Logger LOG =
            LogManager.getLogger(FileSystemNode.class);

    /**
     * This constructor makes a {@code FileSystemNode} for a file.
     * @param parentNode
     * @param size
     */
    public FileSystemNode(FileSystemNode parentNode, int size, String name) {
        this.parentNode = parentNode;
        this.size = size;
        this.flag = true;
        this.name = name;
    }

    /**
     * This constructor makes a {@code FileSystemNode} for a directory.
     * @param parentNode
     * @param directoryFiles
     */
    public FileSystemNode(FileSystemNode parentNode, List<FileSystemNode> directoryFiles, String name) {
        this.parentNode = parentNode;
        this.directoryFiles = directoryFiles;
        this.flag = false;
        this.name = name;
    }

    public void addToList(FileSystemNode node) {
        this.directoryFiles.add(node);
    }

    /**
     * Gets the size of the file or directory.
     * @return size
     */
    public int getSize() {
        if (isFile()) {
            return size;
        }
        int dirSize = 0;
        for (final FileSystemNode node : directoryFiles) {
            dirSize += node.getSize();
        }
        return dirSize;
    }

    /**
     * Gets the size of the file or directory and fires events based upon the parameter.
     * @return size
     */
    public int getSize(int limit) {
        if (isFile()) {
            return size;
        }
        int dirSize = 0;
        for (final FileSystemNode node : directoryFiles) {
            dirSize += node.getSize(limit);
        }
        if (dirSize <= limit) {
            fireNodeEvent(new NodeEvent(this, NodeEventType.SUM, dirSize));
        }
        fireNodeEvent(new NodeEvent(this, NodeEventType.CLOSEST, dirSize));
        return dirSize;
    }

    /**
     * Returns whether the node is a file or not.
     * @return true if file
     */
    public boolean isFile() {
        return flag;
    }

    /**
     * Returns whether the node is a directory or not.
     * @return true if directory
     */
    public boolean isDirectory() {
        return !flag;
    }

    /**
     * Gets the node's files if it's a directory.
     * @return
     */
    public List<FileSystemNode> getDirectoryFiles() {
        if (isFile()) {
            return null;
        }
        return directoryFiles;
    }

    /**
     * Returns the parent node of the node.
     * @return parent node
     */
    public FileSystemNode getParentNode() {
        return parentNode;
    }

    /**
     * Returns the name of the node.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Outputs the node and its contents as a string.
     * @return
     */
    @Override
    public String toString() {
        if (isFile()) {
            return parentNode.name + " -> " + size + " " +  name + "\n";
        }
        String str = name + " " + this.getSize() + "\n";
        for (final FileSystemNode node : directoryFiles) {
            str += node.toString();
        }
        return str;
    }

    /**
     * Adds a {@code NodeEventListener} to the list.
     * @param listener
     */
    public void addNodeEventListener(final NodeEventListener listener) {
        if (listener == null) {
            LOG.error("NodeEventListener of null cannot be added to list.");
            return;
        }
        this.eventListeners.add(listener);
        LOG.trace("New NodeEventListener added to " + getParentNode() + ": " + listener);
    }

    /**
     * Removes a {@code NodeEventListener} to the list.
     * @param listener
     */
    public void removeNodeEventListener(final NodeEventListener listener) {
        this.eventListeners.remove(listener);
        LOG.trace("NodeEventListener removed from " + getName() + ": " + listener);
    }

    /**
     * Fires a {@code NodeEvent} to all listeners.
     * @param nEvent
     */
    private void fireNodeEvent(final NodeEvent nEvent) {
        for (final NodeEventListener listener : this.eventListeners) {
            listener.nodeEventUpdate(nEvent);
        }
    }
}
