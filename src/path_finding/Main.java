package path_finding;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Créer une grille 5x5 avec des obstacles (1 = obstacle, 0 = libre)
        int[][] gridData = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0}
        };

        // Initialisation du scanner pour la saisie de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        
        // Demander les coordonnées de la destination
        System.out.println("Entrez les coordonnées de la destination :");

        int x = -1, y = -1;
        boolean validInput = false;

        // Vérifier que les coordonnées sont valides
        while (!validInput) {
            try {
                System.out.print("x (entre 0 et 4) : ");
                x = Integer.parseInt(scanner.nextLine());

                System.out.print("y (entre 0 et 4) : ");
                y = Integer.parseInt(scanner.nextLine());

                // Vérifier si les coordonnées sont dans les limites de la grille
                if (x < 0 || x >= gridData.length || y < 0 || y >= gridData[0].length) {
                    System.out.println("Les coordonnées sont hors de la grille, essayez encore.");
                    continue;
                }

                // Vérifier si la destination est un obstacle
                if (gridData[x][y] == 1) {
                    System.out.println("La destination est un obstacle. Choisissez une autre destination.");
                } else {
                    validInput = true;  // Les coordonnées sont valides
                    System.out.println("Destination acceptée à (" + x + ", " + y + ").");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer des valeurs entières valides.");
            }
        }

        // Créer un objet Graph représentant la grille
        Graph graph = new Graph(gridData);

        // Définir le point de départ et le point d'arrivée du robot
        Node startNode = new Node(0, 0);  // Le robot commence en haut à gauche
        Node destinationNode = new Node(x, y);  // Le robot veut atteindre la destination choisie par l'utilisateur

        // Créer une instance de l'algorithme de pathfinding (Dijkstra ici)
        PathfindingAlgorithm dijkstra = new Dijkstra();

        // Créer une instance du robot
        Robot robot = new Robot(startNode, dijkstra, graph);

        // Afficher la grille initiale et la position du robot
        System.out.println("Grille initiale :");
        robot.render();

        // Naviguer vers la destination
        System.out.println("\nLe robot commence à se déplacer...");
        robot.navigate(destinationNode);

        // Fermer le scanner à la fin
        scanner.close();
    }
}
