import java.util.ArrayList;
import java.util.List;

public class FloorPlan {

	private List<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>> ();
	
	public FloorPlan() {
	}
	
	public FloorPlan(List<ArrayList<Integer>> m) {
		this.matrix = m;
	}
	
	public List<ArrayList<Integer>> getMatrix() {
		return matrix;
	}
	

	/***
	 * This method is used to find the shortest path to the nearest exit.
	 * @param x: starting coordinate x;
	 * @param y: starting coordinate y;
	 * @return a list of coordinates on the shortest path.
	 */
	public List<ArrayList<Integer>> calculatePath(int x, int y){
		// Need to be implemented by SHB.
		
		// Handling exception: if matrix[x][y] != 0 throw exception.
		
		
		return new ArrayList<ArrayList<Integer>>();
	}
	
	/***
	 * This method is used to display all the entries in our matrix.
	 */
	public void displayMatrix() {
		
	}
	
	
}
