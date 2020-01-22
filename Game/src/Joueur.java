import java.util.ArrayList;

public class Joueur extends Mobile {

	private ArrayList<Direction> histo;
	private int nbCoups;

	public Joueur(Configuration configuration, Position position) {
		super(Type.JOUEUR, configuration, position);
		this.histo = new ArrayList<Direction>();
	}

	public Joueur(Configuration configuration, Joueur joueur) {
		super(joueur.type, configuration, joueur.position);
		this.histo = new ArrayList<Direction>();
	}

	public ArrayList<Direction> getHisto() {
		return histo;
	}

	public boolean bougerVers(Direction direction) { // deplacement

		histo.add(direction);
		Position pos = this.position.add(direction);

		if (this.config.estCaisse(pos)) {
			this.config.get(pos).bougerVers(direction);

			if (!(this.config.get(pos).bougerVers(direction))) {
				return false;
			}

			setPosition(pos);
			nbCoups++;

			return true;
		}

		if (this.config.estVide(pos)) {
			histo.add(direction);
			setPosition(pos);

			return true;
		}

		return false;

	}

	public int getNbCoups() {
		return this.nbCoups;
	}
}
