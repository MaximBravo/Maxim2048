package maximbravo.com.Maxim2048;

import java.util.Scanner;
public class Play2048 {
	public static void main(String[] args){
		Board b = new Board();
		b.drawBoard();
		System.out.println("______________________________________________________");
		Scanner keyboard = new Scanner(System.in);
	    boolean game = true;
		while(game){
			String direction = keyboard.next();
			switch(direction){
			case "w":
				game = b.updateBoard(0);
				break;
			case "a":
				game = b.updateBoard(3);
				break;
			case "s":
				game = b.updateBoard(2);
				break;
			case "d":
				game = b.updateBoard(1);
				break;
			}
			
			b.drawBoard();
			System.out.println("______________________________________________________");
			
		}
//		System.out.println("______________________________________________________");
//		b.updateBoard(3);
//		b.drawBoard();
	}
}
