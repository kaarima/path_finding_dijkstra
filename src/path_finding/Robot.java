package path_finding;

import java.util.List;

public  class Robot implements Moveble, Renderable {
    private Node position;  // Position actuelle du robot
    private PathfindingAlgorithm pathfindingAlgorithm;  // Algorithme pour trouver le chemin
    private Graph graph;  // Grille du monde

    // Constructeur
    public Robot(Node startPosition, PathfindingAlgorithm pathfindingAlgorithm, Graph graph) {
        this.position = startPosition;
        this.pathfindingAlgorithm = pathfindingAlgorithm;
        this.graph = graph;
    }

    // Getter pour la position actuelle
    public Node getPosition() {
        return position;
    }

    // Implémentation de la méthode move() de l'interface Moveble
    public void move(Node nextPosition) {
        // Déplacer le robot vers la nouvelle position
        this.position = nextPosition;  // Mise à jour de la position du robot
        System.out.println("Le robot se déplace vers la position (" + position.getX() + ", " + position.getY() + ")");
    }

    // Implémentation de la méthode render() de l'interface Renderable
    @Override
    public void render() {
        // Affichage de la grille avec la position du robot
        System.out.println("Affichage de la grille actuelle : ");
        for (int i = 0; i < graph.getGrid().length; i++) {
            for (int j = 0; j < graph.getGrid()[i].length; j++) {
                if (position.getX() == i && position.getY() == j) {
                    System.out.print("R ");  // Afficher le robot à sa position
                } else if (graph.getGrid()[i][j] == 1) {
                    System.out.print("# ");  // Afficher un obstacle
                } else {
                    System.out.print(". ");  // Afficher une case libre
                }
            }
            System.out.println();  // Nouvelle ligne pour la grille
        }
    }

    // Méthode pour déplacer le robot vers la destination
    public void navigate(Node destination) {
        try {
            // Utilisation de l'algorithme pour trouver le chemin
            List<Node> path = pathfindingAlgorithm.findShortestPath(this.position, destination, graph);

            System.out.println("Chemin trouvé :");
            // Affichage du chemin trouvé
            for (Node node : path) {
                this.position = node;
                render();  // Affichage de la nouvelle position à chaque étape
                move(node);  // Déplacement du robot vers le nœud suivant
                try {
                    Thread.sleep(1000);  // Pause pour simuler un déplacement (1 seconde par étape)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Vérifier si le robot est arrivé à destination et afficher le message
            if (position.equals(destination)) {
                System.out.println("Ouiaauauaiaia ! On est arrivé à destination !");
            }

        } catch (PathfindingException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
}
