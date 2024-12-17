package path_finding;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int[][] grid;  // Représente la grille (0 = libre, 1 = obstacle)

    // Constructeur de la classe Graph
    public Graph(int[][] grid) {
        this.grid = grid;
    }

    // Méthode pour obtenir la grille
    public int[][] getGrid() {
        return grid;
    }

    // Méthode pour obtenir les voisins d'un nœud (haut, bas, gauche, droite)
    public List<Node> getNeighbors(Node node) throws PathfindingException {
        List<Node> neighbors = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();

        // Vérifier les voisins autour du nœud
        if (x > 0 && grid[x - 1][y] != 1) neighbors.add(new Node(x - 1, y));  // Haut
        if (x < grid.length - 1 && grid[x + 1][y] != 1) neighbors.add(new Node(x + 1, y));  // Bas
        if (y > 0 && grid[x][y - 1] != 1) neighbors.add(new Node(x, y - 1));  // Gauche
        if (y < grid[0].length - 1 && grid[x][y + 1] != 1) neighbors.add(new Node(x, y + 1));  // Droite

        if (neighbors.isEmpty()) {
            throw new PathfindingException("No accessible neighbors found for node (" + x + ", " + y + ")");
        }
        return neighbors;
    }
}
