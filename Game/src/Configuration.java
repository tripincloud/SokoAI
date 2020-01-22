
//import java.util.ArrayList;
import java.util.*;

public class Configuration {

	private ArrayList<Caisse> caisses;
	public Joueur joueur;
	public Niveau niveau;

	public Configuration(Niveau niveau, Position positionJoueur) {
		// constructeur
		this.niveau = new Niveau(niveau.getX(), niveau.getY());
		this.joueur = new Joueur(this, positionJoueur);
		this.caisses = new ArrayList<Caisse>();
	}

	public Configuration(Configuration configuration) {
		// constructeur par copie
		this.caisses = configuration.caisses;
		this.joueur = configuration.joueur;
		this.niveau = configuration.niveau;
	}

	public ArrayList<Caisse> getCaisses() {
		return this.caisses;
	}
	
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public Niveau getNiveau() {
		return this.niveau;
	}

	public int getX() {
		return this.niveau.getX();
	}

	public int getY() {
		return this.niveau.getY();
	}

	public boolean estCaisse(Position position) { // verifie si une caisse est presente a la position envoyee
		for (Caisse c : this.caisses) {
			if (c.position.equals(position)) {
				return true;
			}
		}
		return false;
	}

	public boolean addCaisse(Position position) { // Ajout d'une caisse
		Caisse c = new Caisse(this, position);

		if (!(this.estCaisse(position))) {// on verifie qu'il n'y a pas deja une caisse a cette position
			this.caisses.add(c);
			return true;
		}
		return false;

	}

	public boolean estCible(Position position) {
		ArrayList<Position> cibles = new ArrayList<Position>();
		cibles = this.niveau.getCibles();

		if (cibles.contains(position)) {
			return true;
		}
		return false;
	}

	public boolean estVide(Position position) {
		return this.niveau.estVide(position);
	}

	public Element get(Position position) {
		if (this.joueur.getPosition().equals(position)) {
			return this.joueur;
		}
		for (Caisse c : this.getCaisses()) {
			if (c.getPosition().equals(position)) {
				return c;
			}
		}
		return niveau.get(position);
	}

	public boolean bougerJoueurVers(Direction direction) {
		return this.joueur.bougerVers(direction);
	}

	public boolean victoire() { // verifie la victoire ou non du joueur
		int cpt = 0;

		if (this.caisses.size() != this.niveau.getCibles().size()) {
			System.out.println("non");
			return false;
		}
		for (Caisse a : this.caisses) {
			for (Position b : this.niveau.getCibles()) {
				if (a.position.equals(b)) {
					cpt++;
				}
			}
		}
		if (cpt == this.caisses.size()) {
			System.out.println("Partie Gagnee !");
			return true;
		}

		return false;
	}

	public String toString() {
		String s = "";
		Position p;

		for (int i = 0; i < this.niveau.getX(); i++) {
			s += "\n";

			for (int j = 0; j < this.niveau.getY(); j++) {
				p = new Position(i, j);

				if (this.estCible(p) && p.equals(this.joueur.position)) {
					s += "+ ";
				} 
				else if (this.estCaisse(p) && this.estCible(p)) {
					s += "* ";
				}
				else if (this.estCaisse(p)) {
					s += "$ ";
				}
				else if (this.niveau.get(p) instanceof Case && this.estCible(p)) {
					s += ". ";
				} 
				else if (this.niveau.get(p) instanceof Mur) {
					s += "# ";
				}
				else if (p.equals(this.joueur.position)) {
					s += "@ ";
				}
				else {
					s += "  ";
				}
			}
		}

		return s;
	}

}
