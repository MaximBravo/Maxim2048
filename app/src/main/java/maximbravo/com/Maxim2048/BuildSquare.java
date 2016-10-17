package maximbravo.com.Maxim2048;

import android.support.annotation.Nullable;

import java.util.ArrayList;

public class BuildSquare {
	public BuildSquare(){
		
	}
	@Nullable
	public static ArrayList<String> buildSquare(int id){
		Rules rules = new Rules();
		switch(id){
			case 0:
				return rules.emptySquare;
			case 2:
				return rules.twoSquare;
			case 4:
				return rules.fourSquare; 
			case 8:
				return rules.eightSqure;
			case 16:
				return rules.sixteenSquare;
		}
		return null;
	}
}
