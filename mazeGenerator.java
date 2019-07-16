import java.util.Random;
public class mazeGenerator {
	// stores maze
	private int[] maze;
	// random order to have each spot touched at least once
	private int[] spotOrder;
	// used for arithmetic, is the length of the maze
	private int sizeOfMaze;
	// union set to check if things are connected
	unionSet connector;
	// makes the entire maze in constructor
	public mazeGenerator(int n)
	{
		// i use the sizeOfMaze for arithmetic on edges
		sizeOfMaze = n;
		// initialize maze
		maze = new int[sizeOfMaze*sizeOfMaze];
		// initialize the array for randomly pick spots to mazify
		spotOrder = new int[sizeOfMaze*sizeOfMaze];
		// makes maze like the start position were each spot = F
		iniMaze((sizeOfMaze*sizeOfMaze)-1);
		// sets up random order to check each spot
		setUpOrder();
		// initialize the union set
		connector = new unionSet(sizeOfMaze*sizeOfMaze);
		int i = 0;
		// checks if the union set is just one set, if not it keeps making the maze
		while(!(connector.isOneSet()))
		{
			connectToAdjacent(spotOrder[i]);
			i++;
			if (i == spotOrder.length)
				i = 0;
		}
	}
	private void setUpOrder() 
	{
		Random rng = new Random();
		// put each spot in order in the array
		for(int i = 0; i < spotOrder.length; i++)
			spotOrder[i] = i;
		// then i randomly swap spots in the array
		for (int i = 0; i < spotOrder.length; i++)
			Swap(rng.nextInt(spotOrder.length), i);
	}
	// since i only use swap in setUpOrder, its hardcoded to swap only on spotOrder
	private void Swap(int i, int n)
  	{
		int temp = spotOrder[i];
		spotOrder[i] = spotOrder[n];
		spotOrder[n] = temp;
  	}
	// after it picks which spot, this randomly picks and ajacent spot to connect to
	private void connectToAdjacent(int n)
	{
		Random rng = new Random();
		int spot = rng.nextInt(4);
		if (spot == 0)
			rmTopWall(n);
		else if (spot == 1)
			rmBotWall(n);
		else if (spot == 2)
			rmLeftWall(n);
		else if (spot == 3)
			rmRightWall(n);		
	}
	// removes right wall, if right edge it removes left wall instead
	private void rmRightWall(int n) 
	{
		if ((n+1) % sizeOfMaze == 0)
		{
			rmLeftWall(n);
			//return;
		}
		else if (connector.find(n) != connector.find(n+1))
		{
			maze[n] -= 1;
			maze[n+1] -= 4;
			connector.union(n, n+1);
		}
	}
	// removes left wall, if left edge it removes right wall instead
	private void rmLeftWall(int n) 
	{
		if (n % sizeOfMaze == 0)
		{
			rmRightWall(n);
			//return;
		}
		else if (connector.find(n) != connector.find(n-1))
			{
				maze[n] -= 4;
				maze[n-1] -= 1;
				connector.union(n, n-1);
			}
	}
	// removes bot wall, if bot edge it removes top wall instead
	private void rmBotWall(int n) 
	{
		if (n >= (sizeOfMaze*sizeOfMaze) - sizeOfMaze && n < sizeOfMaze*sizeOfMaze)
			rmTopWall(n);
		else if (connector.find(n) != connector.find(n+sizeOfMaze))
		{
			maze[n] -= 2;
			maze[n+sizeOfMaze] -= 8;
			connector.union(n, n+sizeOfMaze);
		}
	}
	// removes top wall, if top edge it removes bot wall instead
	private void rmTopWall(int n)
	{
		if (n < sizeOfMaze)
			rmBotWall(n);
		else if (connector.find(n) != connector.find(n-sizeOfMaze))
			{
				maze[n] -= 8;
				maze[n-sizeOfMaze] -= 2;
				connector.union(n, n-sizeOfMaze);
			}
	}
	// makes the maze with all F's, other than starting and ending points
	private void iniMaze(int x) 
	{
		maze[0] = 11;
		maze[x] = 14;
		for(int i = 1; i < x; i++)
				maze[i] = 15;
	}
	public int[] getMazeArray() {
		return maze;
	}
	// to string method of maze
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < maze.length; i++)
		{
			char hex = 'a';
			if(maze[i] > 9)
			{
				if(maze[i] == 10)
					hex = 'A';
				if(maze[i] == 11)
					hex = 'B';
				if(maze[i] == 12)
					hex = 'C';
				if(maze[i] == 13)
					hex = 'D';
				if(maze[i] == 14)
					hex = 'E';
				if(maze[i] == 15)
					hex = 'F';
				result.append(hex);
			}
			else
				result.append(maze[i]);
			if((i + 1) % sizeOfMaze == 0)
				result.append("\n");
		}
		return result.toString();
	}
}
