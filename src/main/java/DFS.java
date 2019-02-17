import java.util.LinkedList;
import java.util.Stack;

public class DFS {
	int count = 0;
	int [][]dir = {{1,0},{0,1},{-1,0},{0,-1}};
	Stack<Coordinate> s = new Stack<Coordinate>();
	private int [][]Map;		//input a map
	private int [][]Mark;		//represents the minium step, mark Mark with 0 and -1
	private int cols; 			//Number of colomns
	int rows; 			//Number of rows
	
	public DFS(int [][]map) {
		this.cols = map[0].length;
		this.rows = map.length;
		this.Map = map;
		this.Mark = buildMark(map); 
	}
	
	private static int[][] buildMark(int[][] map){
		int [][]re = new int[map.length][map[0].length];
		for(int i = 0; i<map.length; i++) {
			for(int j = 0; j<map[0].length; j++) {
				if(map[i][j] == 1)
					re[i][j] = -1;
			}
		}
		return re;
	}
	
	public boolean inBound(int i, int j) {	//judge if out of bound
		return(i>=0 && j>=0 && i<rows && j<cols);
	}
	
	public int dfs(Coordinate target) {
		s.push(target);							//initialize queue with Target coordinate
		Mark[target.x][target.y] = 1;
		count = 1;								//initialize count to 1
		//while the queue is not empty(has coordinate ready to be searched
		int i = s.peek().x;					//transfer coordinate to i,j
		int j = s.peek().y;
		int tempi, tempj;
		for(int t= 0;  t< 4; t++) {			//find adjacent, not out of bound coordinate
			tempi = i + dir[t][0];
			tempj = j + dir[t][1];
			if(inBound(tempi,tempj) && Mark[tempi][tempj] == 0) {
				count++;					
				Mark[tempi][tempj] = count;	//mark with distance
				System.out.println("("+tempi+","+tempj+")"+" "+count);
				Coordinate newCor = new Coordinate(tempi,tempj);
				s.push(newCor);				//add coordinate to queue
				dfs(newCor);
				s.pop();
			}
		}

		return 0;
		//return Mark[Start.x][Start.y];			//return the distance
	}
	
}
