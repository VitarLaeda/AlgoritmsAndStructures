import static java.util.Objects.requireNonNull;

public class Edge {

    public final Vertex from;
    public final Vertex to;

    public Edge(Vertex from, Vertex to) {
        this.from = requireNonNull(from);
        this.to = requireNonNull(to);
    }

    @Override
    public String toString() {
        return "Edge{" + from.label + "->" + to.label + '}';
    }
}
