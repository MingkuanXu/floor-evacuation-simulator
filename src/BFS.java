import java.util.*;

public class BFS {
	int count = 0;
	int [][]dir = {{1,0},{0,1},{-1,0},{0,-1}};
	Queue<Coordinate> q = new LinkedList<>();
	int [][]Map;		//input a map
	int [][]Mark;		//represents the minium step, mark Mark with 0 and -1
	int cols; 			//Number of colomns
	int rows; 			//Number of rows
		
	public boolean inBound(int i, int j) {	//judge if out of bound
		return(i>=0 && j>=0 && i<cols && j<rows);
	}
	
	public int bfs(Coordinate target, Coordinate Start) {
		q.add(target);							//initialize queue with Target coordinate
		count = 1;								//initialize count to 1
		while(q.peek()!=null) {					//while the queue is not empty(has coordinate ready to be searched
			int i = q.peek().x;					//transfer coordinate to i,j
			int j = q.peek().y;
			int tempi, tempj;
			for(int t= 0;  t< 4; t++) {			//find adjacent, not out of bound coordinate
				tempi = i + dir[t][0];
				tempj = j + dir[t][1];
				if(inBound(tempi,tempj) && Mark[tempi][tempj] == 0) {
					count++;					
					Mark[tempi][tempj] = count;	//mark with distance
					Coordinate newCor = new Coordinate(tempi,tempj);
					q.add(newCor);				//add coordinate to queue
				}
			}
			if(q.remove().equals(Start))	break; //if the coordinate just processed is Start, end search
		}
		return Mark[Start.x][Start.y];			//return the distance
	}
	
}
