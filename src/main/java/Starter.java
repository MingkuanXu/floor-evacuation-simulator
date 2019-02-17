public class Starter {

	/***
	 * This method prints all the coordinates in a path returned by the search method.
	 * @param coordinates
	 */
	public static void printPathCoordinates(int[][] coordinates) {
		for(int[] each:coordinates) {
				System.out.printf("(%d, %d)\n", each[0], each[1]);
		}
		return;
	}
	public static void main(String[] args) {
		
//		String filename = "test.jpeg";
//		FloorPlan fp = LoadFromPicture.loadFromPicture(filename);
	
		//Test case parameters
		int x = 0;
		int y = 0;
		int[][] m = {
				{0,1,1,1,1,1,1},
				{1,0,0,0,0,0,0},
				{1,1,0,0,0,0,0},
				{1,1,1,1,0,1,1}
				};
		FloorPlan fp = new FloorPlan(m);
		
		// Display matrix
		System.out.println("The input matrix is: ");
		fp.displayMatrix(); 
		
		// Find path
		/*int[][] path = fp.simpleSearchPath(x, y);
//		int[][] path = fp.dfsSearchPath(x, y);
		
		// Display coordinates
		System.out.printf("Starting from coordinate (%d,%d), find path:\n",x,y);
		printPathCoordinates(path);
		*/
		BFS try1 = new BFS(m);
		Coordinate st = new Coordinate(1,1);
		Coordinate end = new Coordinate(3,4);
		System.out.println(try1.bfs(end, st));
		try1.findRoute(end, st, try1.Mark);
	} 
}
