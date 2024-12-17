package path_finding;

public interface Moveble {
    void move();  // Méthode pour déplacer l'entité, à implémenter dans les classes concrètes

	void move(Node nextPosition);
}

