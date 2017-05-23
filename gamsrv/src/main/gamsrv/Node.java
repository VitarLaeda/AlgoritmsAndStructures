package gamsrv;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Node {

    public enum Type {
        CLIENT, ROUTER
    }

    public final Type type;
    public final int id;
    public final List<Connection> connections;

    public Node(Type type, int id) {
        this(type, id, new ArrayList<>());
    }

    public Node(Type type, int id, List<Connection> connections) {
        this.type = requireNonNull(type);
        this.id = id;
        this.connections = requireNonNull(connections);
    }

    @Override
    public String toString() {
        return "\n\tgamsrv.Node{" +
                "type=" + type +
                ", id=" + id +
                ", connections=" + connections +
                '}';
    }
}