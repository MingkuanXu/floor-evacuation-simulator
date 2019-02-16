public class Starter {

	public static void main(String[] args) {
		String filename = "test.jpeg";
		FloorPlan fp = LoadFromPicture.loadFromPicture(filename);
	
		//Test case
		int x = 0;
		int y = 0;
		
		fp.displayMatrix();
		System.out.print(fp.calculatePath(x, y));

	} 
}
