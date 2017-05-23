import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Govern {

    private enum VisitStatus { UNVISITED, VISIT_IN_PROGRESS, VISITED }

    public static void main(String[] args) {
        Path inputPath = args.length >= 1 ? Paths.get(args[0]) : Paths.get("govern.in");
        Path outputPath = args.length >= 2 ? Paths.get(args[1]) : Paths.get("govern.out");

        Graph graph = readGraph(inputPath);
        Iterable<Vertex> topoSortOrder = tarjansTopoSort(graph);
        writeTopoSortOrder(outputPath, topoSortOrder);
    }

    private static void writeTopoSortOrder(Path outputPath, Iterable<Vertex> topoSortOrder) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath)) {
            for (Vertex vertex : topoSortOrder) {
                bufferedWriter.write(vertex.label);
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to save topo sort order", ex);
        }
    }

    private static Iterable<Vertex> tarjansTopoSort(Graph graph) {
        List<Vertex> vertices = graph.vertices;
        int verticesCount = vertices.size();

        EnumMap<VisitStatus, Set<Vertex>> verticesVisitStatus = new EnumMap<>(VisitStatus.class);
        verticesVisitStatus.put(VisitStatus.UNVISITED, new HashSet<>(vertices));
        verticesVisitStatus.put(VisitStatus.VISIT_IN_PROGRESS, new HashSet<>(verticesCount));
        verticesVisitStatus.put(VisitStatus.VISITED, new LinkedHashSet<>(verticesCount));

        visit(vertices.get(0), verticesVisitStatus);

        for (Vertex unvisitedVertex : verticesVisitStatus.get(VisitStatus.UNVISITED)) {
            visit(unvisitedVertex, verticesVisitStatus);
        }

        verticesVisitStatus.get(VisitStatus.UNVISITED).clear(); // Sort of hack

        return verticesVisitStatus.get(VisitStatus.VISITED);
    }

    private static void visit(Vertex vertex, Map<VisitStatus, Set<Vertex>> verticesVisitStatus) {

        if (verticesVisitStatus.get(VisitStatus.VISITED).contains(vertex)) {
            return;
        }

        if (verticesVisitStatus.get(VisitStatus.VISIT_IN_PROGRESS).contains(vertex)) {
            throw new IllegalStateException("not a DAG!");
        }
        verticesVisitStatus.get(VisitStatus.VISIT_IN_PROGRESS).add(vertex);

        for (Edge edge : vertex.outboundEdges) {
            visit(edge.to, verticesVisitStatus);
        }

        verticesVisitStatus.get(VisitStatus.VISITED).add(vertex);
    }

    private static Graph readGraph(Path inputPath) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(inputPath)) {
            Map<String, Vertex> vertices = new HashMap<>();
            Graph graph = new Graph();

            for (String line; (line = bufferedReader.readLine()) != null;) {
                String[] pair = line.split(" ");

                String labelFrom = pair[0];
                Vertex vertexFrom = vertices.computeIfAbsent(labelFrom, Vertex::new);

                if (pair.length == 2) {
                    String labelTo = pair[1];
                    Vertex vertexTo = vertices.computeIfAbsent(labelTo, Vertex::new);

                    Edge edge = new Edge(vertexFrom, vertexTo);
                    vertexFrom.outboundEdges.add(edge);
                    graph.edges.add(edge);
                }
            }

            graph.vertices.addAll(vertices.values());

            return graph;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to read the graph", ex);
        }
    }
}
