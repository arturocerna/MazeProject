import java.util.Scanner;
public class p6 {
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		mazeGenerator maze = new mazeGenerator(input.nextInt());
		System.out.println(maze);	
	}
}
