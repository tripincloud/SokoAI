
public class Mobile extends Element {

	public Configuration config;
	public Position position;

	public Mobile(Type t, Configuration conf, Position pos) {
		super(t);
		this.config = conf;
		this.position = pos;
	}

	public boolean bougerVers(Direction d) {
		return true;
	}

	public boolean setPosition(Position p) {
		this.position = p;

		if (this.position == p) {
			return true;
		}

		return false;
	}

	public Position getPosition() {
		return position;

	}

}
