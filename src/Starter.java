public class Starter {

	public static void main(String[] args) {
		
//		String filename = "test.jpeg";
//		FloorPlan fp = LoadFromPicture.loadFromPicture(filename);
	
		//Test case
		int x = 0;
		int y = 0;
		int[][] m = {
				{0,1,1,1,1,1,1},
				{1,0,0,0,0,0,0},
				{1,1,0,0,0,0,0},
				{1,1,1,1,0,1,1}
				};
		FloorPlan fp = new FloorPlan(m);
		int[][] result = fp.simplySearchPath(x, y);
		System.out.print("Test");
		for(int[] line:result) {
			for(int each:line) {
				System.out.print(each);
			}
			System.out.println();
		}
		
		fp.displayMatrix();
		//System.out.print(fp.dfsSearchPath(x, y));
		
		

	} 
}
