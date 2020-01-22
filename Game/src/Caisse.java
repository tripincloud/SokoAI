
public class Caisse extends Mobile {

	public Caisse(Configuration config, Position position) {

		super(Type.CAISSE, config, position);

	}

	public boolean bougerVers(Direction direction) {

		Position pos = this.position.add(direction);

		if (this.config.estVide(pos) && !(this.config.estCaisse(pos))) {
			setPosition(pos);
			return true;
		}

		return false;

	}

}
