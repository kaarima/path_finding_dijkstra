package path_finding;

public class Node {
    private int x, y;  // Position du nœud dans la grille
    private int cost;  // Coût pour atteindre ce nœud
    private Node parent;  // Référence au parent, utilisé pour retrouver le chemin

    // Constructeur
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.cost = Integer.MAX_VALUE;  // Le coût est initialement à l'infini
        this.parent = null;  // Pas de parent au départ
    }

    // Accesseurs (getters)
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCost() {
        return cost;
    }

    public Node getParent() {
        return parent;
    }

    // Mutateurs (setters)
    public void setX(int x) {
        this.x = x;  // Modifier la position x
    }

    public void setY(int y) {
        this.y = y;  // Modifier la position y
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    // Méthode equals pour comparer les nœuds (utile pour les structures de données comme HashSet)
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node other = (Node) obj;
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
