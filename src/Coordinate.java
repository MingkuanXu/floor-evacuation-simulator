
public class Coordinate {
	int x;
	int y;
	public Coordinate(int a, int b) {
		this.x = a;
		this.y = b;
	}
<<<<<<< HEAD
=======
	
	public boolean equals(Coordinate a) {
		return (a.x==this.x && a.y==this.y);
	}
	
	@Override
	public String toString() {
		return("(" + this.x + "," + this.y + ")");
	}
>>>>>>> d5f292fb431c418855fd0a39fa7261e6f1a757a4
}
