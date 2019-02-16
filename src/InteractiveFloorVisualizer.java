import java.awt.Font;

class InteractiveFloorVisualizer{
	
    private static final int DEFAULT_SIZE = 512;
    private static final double ZOOM_CONSTANT = 1.8;

	public static void main(String[] args) {
		
		  
		int[][] matrix = {
					{0,1,1,1,1,1,1},
					{1,0,0,0,0,0,0},
					{1,1,0,0,0,0,0},
					{1,1,1,1,0,1,2},
					{2,1,1,0,0,0,0}
					};
//		int[][] matrix = {
//				{0,1,1},
//				{1,0,0},
//				{1,1,0},
//				{1,1,1},
//				{1,1,1},
//				{1,1,0}
//				};
//		int[][] matrix = {
//		{0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1},
//		{1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1},
//		{1,1,0,0,0,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1},
//		{1,1,1,1,0,1,2,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1}
//		};
	    int M = matrix.length;
	    int N = matrix[0].length;
//	    int N = 20;
	    
	    System.out.println(M/2.0);
	    System.out.println(N/2.0);
	    

	    // turn on animation mode
	    StdDraw.show(0);
	    // set background, leave margin for writing
	    StdDraw.setCanvasSize((int)(ZOOM_CONSTANT * DEFAULT_SIZE * N / Math.max(M,N)), 
	    						(int)(ZOOM_CONSTANT * DEFAULT_SIZE * M / Math.max(M,N)));
	    StdDraw.setXscale(-0.5, N+0.5);
	    StdDraw.setYscale(-0.5, M+0.5);
	    StdDraw.clear();
	    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
	    StdDraw.filledRectangle(N/2.0, M/2.0, N/2.0,M/2.0);
//      StdDraw.filledSquare(N/2.0, N/2.0, N/2.0);

	    
	    
	    // Initialize matrix
	    for (int row = 0; row < M; row++) {
	        for (int col = 0; col < N; col++) {
	            if (matrix[row][col]==0) {
	            		// Empty
	                StdDraw.setPenColor(StdDraw.WHITE);
	            }
	            else if (matrix[row][col]==1) {
	            		// Barrier
	                StdDraw.setPenColor(StdDraw.BLACK);
	            }
	            else if (matrix[row][col]==2){
	            		// Exit
	                StdDraw.setPenColor(StdDraw.RED);
	            }
	            StdDraw.filledSquare(col + 1 - 0.5, M - row -1 + 0.5, 0.43);
	        }
	    }
	    

//	    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
	    while(true) {
            StdDraw.show(2000000);
	    }
	}
}
