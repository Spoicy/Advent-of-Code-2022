package ch.spoicy.day07;

/**
 * This interface is required for classes wanting to use {@code NodeEvent}s.
 */
public interface NodeEventListener {

    /**
     * This method gets called when a node's size is below or closest to a certain value.
     * @param evt
     */
    void nodeEventUpdate(NodeEvent evt);
}
