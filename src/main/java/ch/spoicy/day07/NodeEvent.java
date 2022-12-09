package ch.spoicy.day07;

import java.util.EventObject;

public class NodeEvent extends EventObject {

    private NodeEventType type;
    private int value;

    public NodeEvent(Object source, NodeEventType type, int value) {
        super(source);
        this.type = type;
        this.value = value;
    }

    public NodeEventType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NodeEvent{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
