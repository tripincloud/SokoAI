public class Solver implements SolverInterface {

	private Configuration config;
	private static int totalSteps;

	public Solver(Configuration config) {

		this.config = config;
		totalSteps = 0;

	}

	public void step() { // deplacement aleatoire
		totalSteps++; // incrementation pas totals
		/*
		int random = (int) (Math.random() * 4); // random entre 0 et 4
		System.out.println(random);
		switch (random) {
		case 0:
			this.config.bougerJoueurVers(Direction.DROITE); // droite
			break;
		case 1:
			this.config.bougerJoueurVers(Direction.GAUCHE); // gauche
			break;
		case 2:
			this.config.bougerJoueurVers(Direction.BAS); // bas
			break;
		default:
			this.config.bougerJoueurVers(Direction.HAUT); // haut

		}
		*/

	}

	public int getTotalSteps() {

		return totalSteps; // renvoie nombre de pas totals

	}

	public Configuration getConfiguration() {

		return this.config; // renvoie la configuration

	}

	public void set(Configuration sokoban) {

		this.config = sokoban; // definir la configuration

	}

	public boolean stop() {

		for (Caisse c : this.config.getCaisses()) {

			System.out.println("Caisse : (" + c.position.x + "," + c.position.y + ")");

			Position p = new Position(c.position);

			Position haut = p.add(Direction.HAUT); // haut
			Position bas = p.add(Direction.BAS); // bas
			Position gauche = p.add(Direction.GAUCHE); // gauche
			Position droite = p.add(Direction.DROITE); // droite

			if ( ( (this.config.get(haut).getType() == Type.MUR) && (this.config.get(droite).getType() == Type.MUR) )
					|| ( (this.config.get(haut).getType() == Type.MUR)
							&& (this.config.get(gauche).getType() == Type.MUR) )
					|| ( (this.config.get(bas).getType() == Type.MUR) && (this.config.get(droite).getType() == Type.MUR) )
					|| ( (this.config.get(bas).getType() == Type.MUR)
							&& (this.config.get(gauche).getType() == Type.MUR) ) ) {

				return true;

			}
		}

		return this.config.victoire();

	}

}
