
public class Coordinate {
	int x;
	int y;
	public Coordinate(int a, int b) {
		this.x = a;
		this.y = b;
	}
	
	public boolean equals(Coordinate a) {
		return (a.x==this.x && a.y==this.y);
	}
	
	@Override
	public String toString() {
		return("(" + this.x + "," + this.y + ")");
	}
}
