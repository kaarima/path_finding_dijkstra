package path_finding;

import java.util.*;

public class Dijkstra extends PathfindingAlgorithm {
    @Override
    public List<Node> findShortestPath(Node start, Node destination, Graph graph) throws PathfindingException {
        // File de priorité pour gérer les nœuds à explorer, ordonnés par coût
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        start.setCost(0);  // Le coût de départ est 0
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();  // On prend le nœud avec le coût le plus bas

            if (current.equals(destination)) {
                // Si on atteint le nœud de destination, on reconstruit le chemin
                List<Node> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = current.getParent();
                }
                Collections.reverse(path);  // On inverse le chemin pour avoir du départ à l'arrivée
                return path;
            }

            // Exploration des voisins
            for (Node neighbor : graph.getNeighbors(current)) {
                int newCost = current.getCost() + 1;  // Le coût pour atteindre un voisin (par exemple, chaque étape coûte 1)
                
                // Si le nouveau coût est inférieur au coût déjà connu pour ce voisin
                if (newCost < neighbor.getCost()) {
                    neighbor.setCost(newCost);  // On met à jour le coût
                    neighbor.setParent(current);  // On définit le parent du voisin
                    queue.add(neighbor);  // On ajoute le voisin à la file de priorité
                }
            }
        }

        throw new PathfindingException("Aucun chemin trouvé entre les nœuds.");
    }
}
