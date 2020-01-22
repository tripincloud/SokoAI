
public final class Position {

	public int x;
	public int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position p) {
		this.x = p.x;
		this.y = p.y;
	}

	public Position add(Direction d) {
		return new Position(this.x + d.getDX(), this.y + d.getDY());
	}

	public Position sub(Direction d) {
		return new Position(this.x - d.getDX(), this.y - d.getDY());
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}

		Position p = (Position) o;
		return ((x == p.x) && (y == p.y));
	}

}
