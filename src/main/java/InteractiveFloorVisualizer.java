
public class InteractiveFloorVisualizer implements Runnable{
	
    private static final int DEFAULT_SIZE = 512;
    private static final double ZOOM_CONSTANT = 1.0;
    private static final double EDGE = 0.5;
    
    private static final int EMPTY_VALUE = 0;
    private static final int BLOCKED_VALUE = 1;
    private static final int EXIT_VALUE = 2;
    private static final int PATH_VALUE = 3;
    private static final int BOTTON_VALUE_ADD_EXIT_MODE_ON = 8;
    private static final int BOTTON_VALUE_ADD_EXIT_MODE_OFF = 9;
    
    // Start adding exit if turned on.
    private static boolean addExitMode = false; 
    
    private int[][] matrix;
    private Thread t;
    private String threadName;
    
    public InteractiveFloorVisualizer(String name) {
    		this.threadName = name;
    }

    
	/***
	 * Fill in a given coordinate with current pen color. 
	 * @param row
	 * @param col
	 * @param M is the length of the matrix
	 * @param shape: 's' refers to square and 'c' refers to circle.
	 */
    private static void fillInCoordinates(int row, int col, int M, char shape) {
    		if(shape == 's')
    			StdDraw.filledSquare(col + 1 - EDGE, M - row -1 + EDGE, 0.5);
    		else if(shape == 'c')
    			StdDraw.filledCircle(col + 1 - EDGE, M - row -1 + EDGE, 0.4);
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
	            if (matrix[row][col]==EMPTY_VALUE) {
	            		// Empty
	                StdDraw.setPenColor(StdDraw.WHITE);
		            fillInCoordinates(row,col,M,'s');
	            }
	            else if (matrix[row][col]==BLOCKED_VALUE) {
	            		// Barrier
	                StdDraw.setPenColor(StdDraw.BLACK);
		            fillInCoordinates(row,col,M,'s');
	            }
	            else if (matrix[row][col]==EXIT_VALUE || matrix[row][col]==BOTTON_VALUE_ADD_EXIT_MODE_ON){
	            		// Exit
	                StdDraw.setPenColor(StdDraw.GREEN);
		            fillInCoordinates(row,col,M,'c');
	            }   	
	            else if(matrix[row][col]==BOTTON_VALUE_ADD_EXIT_MODE_OFF || matrix[row][col]==PATH_VALUE) {
	                StdDraw.setPenColor(StdDraw.ORANGE);
		            fillInCoordinates(row,col,M,'s');
	            }   
	        }
	    }
    }
    
    /***
     * Remove all entries occupied by last path search.
     * @param matrix
     * @return
     */
    private static int[][] removeAllPath(int[][] matrix){
    		for(int i=0; i<matrix.length; i++) {
    			for(int j=0; j<matrix[0].length; j++) {
    				if(matrix[i][j]==PATH_VALUE)
    					matrix[i][j]=EMPTY_VALUE;
    			}
    		}
    		return matrix;
    }
    
    /***
     * Add one column and one row to the input matrix with value 9
     * @param matrix
     * 
     */
    private static int[][] extendMatrix(int[][] matrix) {
    		int length = matrix.length;
    		int width = matrix[0].length;		
    		int[][] extendedMatrix = new int[length+1][width+1];
    		
    		// Copy original matrix
    		for(int i=0; i<length; i++) {
    			for(int j=0; j<width; j++) {
    				extendedMatrix[i][j] = matrix[i][j];
    			}
    		}
    		
    		// Initialize new column and row with BOTTON_VALUE
    		for(int i=0; i<length; i++) {
    				extendedMatrix[i][width] = BLOCKED_VALUE;
    		}
    		for(int j=0; j<width; j++) {
				extendedMatrix[length][j] = BLOCKED_VALUE;
		}
    		extendedMatrix[length][width-1] = EMPTY_VALUE;
    		extendedMatrix[length][width] = BOTTON_VALUE_ADD_EXIT_MODE_OFF;
    		
    		return extendedMatrix;
    		
    }
    
    private static int[] getMouseCoordinates(int M, int N) {
        // screen coordinates
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        System.out.printf("Coordinate: (%f, %f)\n",x,y);

        // convert to row i, column j
        int i = (int) (M - Math.floor(y)-1);
        int j = (int) (Math.floor(x));
        System.out.printf("Coordinate: (%d, %d)\n",i,j);

        
        return new int[] {i,j};
    }
    
    private static void visualize(int[][] matrix) {
    		
		matrix = extendMatrix(matrix);
	    int M = matrix.length;
	    int N = matrix[0].length;
	    System.out.printf("M=%d, N=%d\n",M,N);
	    // turn on animation mode
	    StdDraw.show(0);
	    // set background, leave margin for writing
	    StdDraw.setCanvasSize((int)(ZOOM_CONSTANT * DEFAULT_SIZE * N / Math.max(M,N)), 
	    						(int)(ZOOM_CONSTANT * DEFAULT_SIZE * M / Math.max(M,N)));
	    StdDraw.setXscale(-EDGE, N+EDGE);
	    StdDraw.setYscale(-EDGE, M+EDGE);
//      StdDraw.filledSquare(N/2.0, N/2.0, N/2.0);

	    
	    initializeMatrix(M,N,matrix);



//	    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
	    while(true) {
		    	if(StdDraw.mousePressed()) {
		    		initializeMatrix(M,N,matrix);
		    		int pressedCol = getMouseCoordinates(M,N)[0]; 
		    		int pressedRow = getMouseCoordinates(M,N)[1];
		    		System.out.printf("(%d, %d, %d, %d)",pressedRow,pressedCol, M-1, N-1);
		    		if(pressedRow == N-1 && pressedCol == M-1) {
		    			addExitMode = false;
		    			matrix[M-1][N-1] = BOTTON_VALUE_ADD_EXIT_MODE_OFF; 
		    			matrix[M-1][N-2] = EMPTY_VALUE; 
			    		initializeMatrix(M,N,matrix);
		    		}
		    		else if(pressedRow == N-2 && pressedCol == M-1) {
		    			addExitMode = true;
		    			matrix[M-1][N-2] = BOTTON_VALUE_ADD_EXIT_MODE_ON; 
		    			matrix[M-1][N-1] = EMPTY_VALUE; 
			    		initializeMatrix(M,N,matrix);		    			
		    		}
		    		else {
			    		try{
			    			if(matrix[pressedCol][pressedRow]==EMPTY_VALUE || matrix[pressedCol][pressedRow]==PATH_VALUE) {
			    				if(addExitMode) 
			    					matrix[pressedCol][pressedRow] = EXIT_VALUE;
			    				else { 
			    					matrix = removeAllPath(matrix);
			    					matrix[pressedCol][pressedRow] = PATH_VALUE;
			    				}
			    			}
			    		}		    		
			    		catch(IndexOutOfBoundsException e) {
			    			// Nothing needs to be done here
			    		}
		    		}
		    	}
            StdDraw.show(20);
	    }
    }
    
	public static void initializeVisualizer(FloorPlan fp) throws Exception {
		

		fp.displayMatrix();
		
		visualize(fp.getMatrix());
	}
	
	public static void main(String[] args) {
		
		/*
		int[][] matrix = {
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1},
				{1,1,1,1,1,1,1,0,0,0,0,0,1,0,1,1,0,0,0,1,1,1,1,0,1,0,1,1,0,0,1,0,0,1,1,0,1,0,1,1,1,0,1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1},
				{1,1,1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,0,0,0,1},
				{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
				{1,1,1,1,1,1,1,0,0,0,0,1,1,0,1,0,1,0,0,1,0,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,2,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,0,1,0,0,1,0,1,0,1,0,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,0,0,0,1,0,0,1,1,0,1,1,1,0,0,1,0,1,0,1,1,1,1,0,0,0,0,1,0,1,0,1,2,1,1,1,1,0,0,1,0,1,0,0,1,0,1,0,1,0,0,0,0,1},
				{1,1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,1,1,1,0,0,1,0,1,0,0,0,1,0,0,1,0,1,1,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,1,1,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,1,1,1,0,0,1,0,1,0,0,0,1,0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,0,0,0,0,1,1,1,1},
				{1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,0,1,1,1,0,0,1,0,1,0,0,0,1,0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,0,0,1,0,0,0,0,1},
				{1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,0,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,0,1,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,0,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,0,0,0,0,1,1,0,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,0,0,1,0,0,0,1},
				{1,1,1,1,1,1,0,0,1,0,0,1,0,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,1,1,1,0,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,1,0,1,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,1,1,1,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,0,0,0,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,0,1,1,0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,0,0,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,0,1,0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		};
		 */
	
//		
//		int[][] matrix = {{1,0,0,0,0,1},
//				  {1,0,0,1,1,1},
//				  {1,1,1,1,1,1}};
//		visualize(matrix);
}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
		return;
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}
	
	@Override
	public void run() {
		visualize(matrix);
	}

}
