import java.util.ArrayList;
import java.util.List;

public class FloorPlan {

	private int[][] matrix;
	private int length;
	private int width;
	
	public FloorPlan() {
	}
	
	public FloorPlan(int[][] m) {
		this.matrix = m;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	/***
	 * Set the coordinate (x,y) to a specific value. Should be used for debug only.
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setCoordinate(int x, int y, int value) {
		matrix[x][y] = value;
		return;
	}
	

	/***
	 * This method uses dfs to find the shortest path to the nearest exit.
	 * @param x: starting coordinate x;
	 * @param y: starting coordinate y;
	 * @return a list of coordinates on the shortest path.
	 * @author Hongbo Sun
	 */
	public int[][] dfsSearchPath(int x, int y){
		
		if(matrix[x][y]!=0) return new int[0][0]; // If the starting point is not empty, return.
		
		// Need to be implemented by SHB.
		
		return new int[0][0];
	}
	
	
	/***
	 * This method finds a random path without considering any barriers.
	 * Should be used for testing UI only. Will be deleted later.
	 * @author Mingkuan Xu
	 */
	public int[][] simpleSearchPath(int x, int y) {
		
		if(matrix[x][y]!=0) return new int[0][0]; // If the starting point is not empty, return.
		
		int length = matrix.length; // Refer to x coordinate
		int width = matrix[0].length; // Refer to y coordinate
		
		
		List<int[]> coordinates = new ArrayList<int[]>();
		for(int i=x; i<length; i++) { 
				coordinates.add(new int[] {i,y});
		}
		for(int j=y+1; j<width; j++) { 
			coordinates.add(new int[] {length-1,j});
		}

		return coordinates.toArray(new int[0][0]);
		
	}
	
	/***
	 * This method is used to display all the entries in our matrix.
	 */
	public void displayMatrix() {
		for(int i=0; i<matrix.length;i++) {
			for(int j=0; j<matrix[0].length; j++) {
				System.out.printf("%d\t",matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		return;
	}
	
	
}
