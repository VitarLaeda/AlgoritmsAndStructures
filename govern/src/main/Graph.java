import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Graph {

    public final List<Vertex> vertices;
    public final List<Edge> edges;

    public Graph() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = requireNonNull(vertices);
        this.edges = requireNonNull(edges);
    }

    @Override
    public String toString() {
        return "Graph{\n" +
                "\tvertices=" + vertices +
                ", \n\tedges=" + edges +
                "\n}";
    }
}
