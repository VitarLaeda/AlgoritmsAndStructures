import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Vertex {

    public final String label;
    public final List<Edge> outboundEdges;

    public Vertex(String label) {
        this(label, new ArrayList<>());
    }

    public Vertex(String label, List<Edge> outboundEdges) {
        this.label = requireNonNull(label);
        this.outboundEdges = requireNonNull(outboundEdges);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Vertex vertex = (Vertex) obj;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                ", outboundEdges=" + outboundEdges +
                '}';
    }
}
