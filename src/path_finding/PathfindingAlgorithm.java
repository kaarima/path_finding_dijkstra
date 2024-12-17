package path_finding;

import java.util.List;

public abstract class PathfindingAlgorithm {
    public abstract List<Node> findShortestPath(Node start, Node destination, Graph graph) throws PathfindingException;
}
