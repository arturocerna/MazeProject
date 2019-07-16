import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class DisplayMaze {
	// these static members are used to see if there is a wall in the 
	// corresponding direction
	static int[] top = {8, 9, 10, 11, 12, 13, 14, 15};
	static int[] bot = {2, 3, 6, 7, 10, 11, 14, 15};
	static int[] left = {4, 5, 6, 7, 12, 13, 14, 15};
	static int[] right = {1, 3, 5, 7, 9, 11, 13, 15};
	BufferedImage img;
	static int windowSize = 600;
	// the constructor initializes all class variables
	public DisplayMaze(int n, int[] inputMaze) {
		img = new BufferedImage(windowSize+1, windowSize+1, BufferedImage.TYPE_INT_RGB);
		createImage(n, inputMaze);
	}
	private void createImage(int n, int[] inputMaze) {
		Graphics g = img.getGraphics();
		// sets our background to white
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, windowSize+1, windowSize+1);
		// sets our wall color to black
		g.setColor(Color.BLACK);
		// the dimension of each cell
		int cellD = windowSize / n; 
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				int mazeSpot = (y*n) + x;
				if (checkLeftWall(inputMaze[mazeSpot]) == 1) {
					g.drawLine(x * cellD, y * cellD, x * cellD, y * cellD + cellD );
				}
				if (checkRightWall(inputMaze[mazeSpot]) == 1) {
					g.drawLine(x * cellD + cellD, y * cellD, x * cellD + cellD, y * cellD + cellD );
				}
				if (checkTopWall(inputMaze[mazeSpot]) == 1) {
					g.drawLine(x * cellD, y * cellD, x * cellD + cellD, y * cellD);
				}
				if (checkBotWall(inputMaze[mazeSpot]) == 1) {
					g.drawLine(x * cellD, y * cellD + cellD, x * cellD +cellD, y * cellD + cellD );
				}
			}
		}
	}
	private static int checkLeftWall(int check) {
		for(int i = 0; i < left.length; i++)
			if(left[i] == check)
				return 1;
		return 0;
	}
	// checks each spot in the right array to see if there is a wall blocking the path
	private static int checkRightWall(int check) {
		for(int i = 0; i < right.length; i++)
			if(right[i] == check)
				return 1;
		return 0;
	}
	// checks each spot in the bot array to see if there is a wall blocking the path
	private static int checkBotWall(int check) {
		for(int i = 0; i < bot.length; i++)
			if(bot[i] == check)
				return 1;
		return 0;
	}
	// checks each spot in the top array to see if there is a wall blocking the path
	private static int checkTopWall(int check) {
		for(int i = 0; i < top.length; i++)
			if(top[i] == check)
				return 1;
		return 0;
	}

}
