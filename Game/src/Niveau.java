import java.util.ArrayList;

public class Niveau {

	protected Immobile[][] grille;
	protected ArrayList<Position> cibles;
	private final int x;
	private final int y;

	public Niveau(int x, int y) {

		this.x = x;
		this.y = y;
		cibles = new ArrayList<Position>();
		grille = new Immobile[x][y];

		for (int i = 0; i < x; i++) {

			grille[i][0] = new Mur();

			for (int j = 0; j < y; j++) {
				grille[0][j] = new Mur();

				if (grille[i][j] == null) {
					grille[i][j] = new Case();
				}

				grille[x - 1][j] = new Mur();
			}

			grille[i][y - 1] = new Mur();

		} // boucle creant les murs autour de la zone de jeu (aux bords du niveau) et des cases dedans

	}

	public int getX() { // accesseur X
		return this.x;
	}

	public int getY() { // accesseur Y
		return this.y;
	}

	public boolean addMur(Position position) { // ajout d'un mur, renvoie true si succes

		if (this.get(position) instanceof Case) { // si la position est une case
			this.grille[position.x][position.y] = new Mur(); // ajout
			return true; // ajout du mur reussi
		}

		return false; // erreur ajout du mur

	}

	public boolean addCible(Position position) { // ajout d'une cible, renvoie true si succes
		
		// si ce n'est pas un mur ou si ne cotient pas deja une cible a cette position
		if (!(this.get(position) instanceof Mur) && !(estCible(position))) {
			cibles.add(position); // ajout de la cible
			return true; // ajout cible reussie
		}

		return false;// erreur ajout cible

	}

	public boolean estCible(Position position) { // teste si on a une cible a la position
		return this.getCibles().contains(position);
	}

	public boolean estVide(Position position) { // teste si la case ne contient pas de mur
		return !(this.grille[position.x][position.y] instanceof Mur);
	}

	public Immobile get(Position position) { // renvoie l'objet stocke a la position
		return this.grille[position.x][position.y];
	}

	public ArrayList<Position> getCibles() { // accesseur des cibles
		return this.cibles;
	}

}
