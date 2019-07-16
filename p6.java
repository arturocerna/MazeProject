import java.util.Scanner;
import javax.swing.*;
import java.awt.image.BufferedImage;
public class p6 {
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		try {
			int size = input.nextInt();
			mazeGenerator maze = new mazeGenerator(size);
		    DisplayMaze mazeImg = new DisplayMaze(size, maze.getMazeArray());
		    JFrame frame = new JFrame();
		    frame.getContentPane().add(new JLabel(new ImageIcon(mazeImg.img)));
		    frame.pack();
		    frame.setVisible(true);
		} finally {
			input.close();
		}
	}
}
