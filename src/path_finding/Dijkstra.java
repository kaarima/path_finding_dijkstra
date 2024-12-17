package path_finding;

import java.util.*;

public class Dijkstra extends PathfindingAlgorithm {
    @Override
    public List<Node> findShortestPath(Node start, Node destination, Graph graph) throws PathfindingException {
        // File de priorité pour gérer les nœuds à explorer, ordonnés par coût
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        start.setCost(0);  // Le coût de départ est 0
        queue.add(start);
        
        Set<Node> visited = new HashSet<>();  // Ensemble des nœuds déjà visités

        while (!queue.isEmpty()) {
            Node current = queue.poll();  // On prend le nœud avec le coût le plus bas

            // Vérifier si le nœud a déjà été exploré
            if (visited.contains(current)) {
                continue;  // Si c'est déjà exploré, on passe au suivant
            }

            visited.add(current);  // Marquer le nœud comme exploré

            // Si on atteint le nœud de destination
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
                if (visited.contains(neighbor)) {
                    continue;  // Si le voisin a déjà été exploré, on le saute
                }

                int newCost = current.getCost() + 1;  // Le coût pour atteindre un voisin (par exemple, chaque étape coûte 1)
                
                // Si le nouveau coût est inférieur au coût déjà connu pour ce voisin
                if (newCost < neighbor.getCost()) {
                    neighbor.setCost(newCost);  // On met à jour le coût
                    neighbor.setParent(current);  // On définit le parent du voisin
                    queue.add(neighbor);  // On ajoute le voisin à la file de priorité
                }
            }
        }

        // Si on sort de la boucle, cela veut dire qu'on n'a pas trouvé de chemin
        throw new PathfindingException("Aucun chemin trouvé entre les nœuds.");
    }
}
