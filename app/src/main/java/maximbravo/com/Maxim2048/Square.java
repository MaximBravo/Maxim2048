package maximbravo.com.Maxim2048;

import java.util.ArrayList;

public class Square {
	private int id;
	private boolean justAdded;

	private ArrayList<String> stringSquare;
	public Square(int num){
		justAdded = false;
		id = num;
		stringSquare = BuildSquare.buildSquare(id);
	}
	public Square(int num, boolean added){
		justAdded = added;
		id = num;
		stringSquare = BuildSquare.buildSquare(id);
	}
	public String getLine(int lineNum){

		return stringSquare.get(lineNum);
	}
	public boolean getCanAdd(){
		return true;
	}
	public void setJustAdded(boolean setTo){
		justAdded = setTo;
	}
	public String getSquareColor(){
		String result = "";
		result = ColorCoding.getStringColorFor(Rules.scale, id);
		return result;
	}
	public boolean getJustAdded(){
		return justAdded;
	}
	public int getId(){
		return id;
	}
}
