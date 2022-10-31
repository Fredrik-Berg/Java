package mountain;

public class Side {
	Point p1;
	Point p2;

	public Side(Point p1, Point p2) {

		this.p1 = p1;
		this.p2 = p2;

	}

	public Point returnP1() {
		return p1;
	}

	public Point returnP2() {
		return p2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Side) {
			Side s = (Side) obj;
			if ((s.p1.equals(p1) && s.p2.equals(p2)) || (s.p1.equals(p2) && s.p2.equals(p1))) {
				return true;
			}
		}
		return false;

	}

	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}

}
