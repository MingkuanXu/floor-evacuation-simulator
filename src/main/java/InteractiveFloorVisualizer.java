
class InteractiveFloorVisualizer{
	
    private static final int DEFAULT_SIZE = 512;
    private static final double ZOOM_CONSTANT = 1.6;
    
	/***
	 * Fill in a given coordinate with current pen color. 
	 * @param row
	 * @param col
	 * @param M is the length of the matrix
	 * @param shape: 's' refers to square and 'c' refers to circle.
	 */
    private static void fillInCoordinates(int row, int col, int M, char shape) {
    		if(shape == 's')
    			StdDraw.filledSquare(col + 1 - 0.5, M - row -1 + 0.5, 0.5);
    		else if(shape == 'c')
    			StdDraw.filledCircle(col + 1 - 0.5, M - row -1 + 0.5, 0.4);
    }
    
    /***
     * Initialize matrix.
     * @param M
     * @param N
     * @param matrix
     */
    private static void initializeMatrix(int M, int N, int[][] matrix) {
	    StdDraw.clear();
	    StdDraw.setPenColor(StdDraw.GRAY);
	    StdDraw.rectangle(N/2.0, M/2.0, N/2.0,M/2.0);
	    for (int row = 0; row < M; row++) {
	        for (int col = 0; col < N; col++) {
	            if (matrix[row][col]==0) {
	            		// Empty
	                StdDraw.setPenColor(StdDraw.WHITE);
		            fillInCoordinates(row,col,M,'s');
	            }
	            else if (matrix[row][col]==1) {
	            		// Barrier
	                StdDraw.setPenColor(StdDraw.BLACK);
		            fillInCoordinates(row,col,M,'s');
	            }
	            else if (matrix[row][col]==2){
	            		// Exit
	                StdDraw.setPenColor(StdDraw.GREEN);
		            fillInCoordinates(row,col,M,'c');
	            }
	        }
	    }
    }
    
    private static int[] getMouseCoordinates(int N) {
        // screen coordinates
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();

        // convert to row i, column j
        int i = (int) (N - Math.floor(y)-2);
        int j = (int) (1 + Math.floor(x));
        int vi = i-1;
        int vj = j-1;
        
        return new int[] {vi,vj};
    }

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

	    // turn on animation mode
	    StdDraw.show(0);
	    // set background, leave margin for writing
	    StdDraw.setCanvasSize((int)(ZOOM_CONSTANT * DEFAULT_SIZE * N / Math.max(M,N)), 
	    						(int)(ZOOM_CONSTANT * DEFAULT_SIZE * M / Math.max(M,N)));
	    StdDraw.setXscale(-0.5, N+0.5);
	    StdDraw.setYscale(-0.5, M+0.5);
//      StdDraw.filledSquare(N/2.0, N/2.0, N/2.0);

	    
	    initializeMatrix(M,N,matrix);

	    
//	    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
	    while(true) {
		    	if(StdDraw.mousePressed()) {
		    		initializeMatrix(M,N,matrix);
		    		int pressedCol = getMouseCoordinates(N)[0]; 
		    		int pressedRow = getMouseCoordinates(N)[1];
		    		if(matrix[pressedCol][pressedRow]==0) {
		    			StdDraw.setPenColor(StdDraw.ORANGE);
		    			fillInCoordinates(pressedCol,pressedRow,M,'s');
		    		}	
		    	}
            StdDraw.show(20);
	    }
	}

}
