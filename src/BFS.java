import java.util.*;

public class BFS {
	int count = 0;
	int [][]dir = {{1,0},{0,1},{-1,0},{0,-1}};
	Queue<Coordinate> q = new LinkedList<>();
	private int [][]Map;		//input a map
	protected int [][]Mark;		//represents the minium step, mark Mark with 0 and -1
	private int cols; 			//Number of colomns
	int rows; 			//Number of rows
	ArrayList<Coordinate> Route = new ArrayList<Coordinate>();
	
	public BFS(int [][]map) {
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
	
	public boolean bfs(Coordinate target, Coordinate Start) {
		q.add(target);							//initialize queue with Target coordinate
		Mark[target.x][target.y] = 1;
		count = 0;								//initialize count to 1
		while(q.peek()!=null) {					//while the queue is not empty(has coordinate ready to be searched
			int i = q.peek().x;					//transfer coordinate to i,j
			int j = q.peek().y;
			int tempi, tempj;
			for(int t= 0;  t< 4; t++) {			//find adjacent, not out of bound coordinate
				tempi = i + dir[t][0];
				tempj = j + dir[t][1];
				if(inBound(tempi,tempj) && Mark[tempi][tempj] == 0) {

					Mark[tempi][tempj] = Mark[i][j]+1;	//mark with distance
					System.out.println("("+tempi+","+tempj+")"+" "+(Mark[tempi][tempj]-1));
					Coordinate newCor = new Coordinate(tempi,tempj);
					if(newCor.equals(Start))	return true;
					q.add(newCor);				//add coordinate to queue
				}
			}
			q.remove();
		}
		return false;			//return the distance
	}
	
	public ArrayList<Coordinate> findRoute(Coordinate Target, Coordinate Start, int[][]Mark) {
		int step = Mark[Start.x][Start.y];
		//System.out.println(step);
		Route.add(Start);
		int curi = Start.x, curj = Start.y;
		int tempi, tempj;
		while(--step>0) {
			for(int t= 0;  t< 4; t++) {			//find adjacent, not out of bound coordinate
				tempi = curi + dir[t][0];
				tempj = curj + dir[t][1];
				if(Mark[tempi][tempj] == step) {
					Coordinate newCor = new Coordinate(tempi, tempj);
					System.out.print(newCor.toString() + " ");
					Route.add(newCor);
					curi = tempi;
					curj = tempj;
					break;
				}
			}
		}
		return Route;
	}
}
