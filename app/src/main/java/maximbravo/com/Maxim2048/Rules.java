package maximbravo.com.Maxim2048;

import java.util.ArrayList;

public class Rules {
	public static int scale = 9;
	public Rules(){
		initializeSquares();
	}
	public static ArrayList<String> emptySquare = new ArrayList<String>();
	public static ArrayList<String> twoSquare = new ArrayList<String>();
	public static ArrayList<String> fourSquare = new ArrayList<String>();
	public static ArrayList<String> eightSqure = new ArrayList<String>();
	public static ArrayList<String> sixteenSquare = new ArrayList<String>();
	
	public void initializeSquares(){
		
		emptySquare.add(" *********** ");
		emptySquare.add("|           |");
		emptySquare.add("|           |");
		emptySquare.add("|           |");
		emptySquare.add("|           |");
		emptySquare.add("|           |");
		emptySquare.add(" *********** ");
		
		twoSquare.add(" *********** ");
		twoSquare.add("|           |");
		twoSquare.add("|   2       |");
		twoSquare.add("|           |");
		twoSquare.add("|        2  |");
		twoSquare.add("|           |");
		twoSquare.add(" *********** ");
		
		fourSquare.add(" *********** ");
		fourSquare.add("|           |");
		fourSquare.add("|   4       |");
		fourSquare.add("|           |");
		fourSquare.add("|        4  |");
		fourSquare.add("|           |");
		fourSquare.add(" *********** ");
		
		eightSqure.add(" *********** ");
		eightSqure.add("|           |");
		eightSqure.add("|   8       |");
		eightSqure.add("|           |");
		eightSqure.add("|        8  |");
		eightSqure.add("|           |");
		eightSqure.add(" *********** ");
		
		sixteenSquare.add(" *********** ");
		sixteenSquare.add("|           |");
		sixteenSquare.add("|   16      |");
		sixteenSquare.add("|           |");
		sixteenSquare.add("|       16  |");
		sixteenSquare.add("|           |");
		sixteenSquare.add(" *********** ");
	}

	public static int[] convertToIntArray(Square[] squares) {
		int[] result = new int[4];
		for(int i = 0; i < 4; i++){
			result[i] = squares[i].getId();
		}
		return result;
	}

}
