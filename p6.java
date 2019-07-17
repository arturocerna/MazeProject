import java.util.Scanner;
import javax.swing.*;
public class p6 {
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a number to set dimension of maze.");
		System.out.println("To Exit, Enter a non-integer.");
		JFrame frame = new JFrame();
		JLabel contents = new JLabel();
		try {
			while(input.hasNextInt()) {
				frame.setVisible(false);
				int size = input.nextInt();
				mazeGenerator maze = new mazeGenerator(size);
				DisplayMaze mazeImg = new DisplayMaze(size, maze.getMazeArray());
				contents = new JLabel(new ImageIcon(mazeImg.img));
				frame.getContentPane().removeAll();
				frame.getContentPane().add(contents);
				frame.pack();
				frame.setVisible(true);
			}
		} finally {
			input.close();
		}
		System.exit(0);
	}
}
