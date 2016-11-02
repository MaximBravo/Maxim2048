package maximbravo.com.Maxim2048;

public class Board {
	private Square[][] board = new Square[4][4];
    public static boolean test = false;
	public Board(){
		initializeBoard();
	}
	
	public boolean updateBoard(int direction){
		boolean result = true;
		Matrix m = new Matrix(board);
		result = Matrix.update(direction);
		board = m.getUpdatedBoard();
        MainActivity.updateScore();
		return result;
	}
    public int getHighestSquare(){
        int greatest = 0;
        for(int i =0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                if(board[i][j].getId() > greatest){
                    greatest = board[i][j].getId();
                }
            }
        }
        return greatest;
    }
	public Square[][] getSquareArrayBoard(){
        return board;
    }
	public Square get(int row, int column){
        return board[row][column];
    }
    public void set(int row, int column, int id){
        board[row][column] = new Square(id);
    }
    public void convertIntArrayToBoard(int row, int[] array){
        for(int i = 0; i < 4; i++) {
            board[row][i] = new Square(array[i]);
        }
    }
    public void initializeBoard(String st){
        String str = st;

        if(st.length() < 16 || test){
            initializeBoard();

        } else {
            int count = 0;
            String[] numbers = st.split(",");
            for (int row = 0; row < 4; row++) {
                Square[] s = new Square[4];
                for (int column = 0; column < 4; column++) {
                    s[column] = new Square(Integer.parseInt(numbers[count]));
                    count++;
                }
                board[row] = s;
            }
            MainActivity.helpLeft = Integer.parseInt(numbers[16]);
            if(numbers.length >= 18) {
                MainActivity.score = Integer.parseInt(numbers[17]);
            }
            if(numbers.length >= 19) {
                if(Integer.parseInt(numbers[18]) == 0) {
                    MainActivity.hasNotAdded = true;
                } else{
                    MainActivity.hasNotAdded = false;
                }
            }

        }
    }
	public void initializeBoard(){

        if(test){
            Square[] row1 = {new Square(512), new Square(512), new Square(512), new Square(512)};
            Square[] row2 = {new Square(512), new Square(512), new Square(512), new Square(512)};
            Square[] row3 = {new Square(512), new Square(512), new Square(512), new Square(512)};
            Square[] row4 = {new Square(512), new Square(512), new Square(512), new Square(512)};
            board = new Square[][]{
                    row1,
                    row2,
                    row3,
                    row4
            };
        } else {
            for (int row = 0; row < 4; row++) {
                Square[] s = new Square[4];
                for (int column = 0; column < 4; column++) {
                    s[column] = new Square(0);
                }
                board[row] = s;
            }

            Matrix m = new Matrix(board);
            m.addRandomSquare();
            m.addRandomSquare();

        }
	}
	
	public void drawBoard(){
		for(int i = 0; i < 4; i++){
			drawRow(i);
		}
	}
	public void drawRow(int row){
        for(int i = 0; i < 4; i ++) {
            MainActivity.drawButtonAt(board[row][i], getIdForButton(row, i));
        }
	}
    public int getIdForButton(int row, int column){
        int id = 0;
        switch(row){
            case 0:
                switch (column) {
                    case 0:
                        id = R.id.id1;
                        break;
                    case 1:
                        id = R.id.id2;
                        break;
                    case 2:
                        id = R.id.id3;
                        break;
                    case 3:
                        id = R.id.id4;
                        break;
                }
                break;
            case 1:
                switch (column) {
                    case 0:
                        id = R.id.id5;
                        break;
                    case 1:
                        id = R.id.id6;
                        break;
                    case 2:
                        id = R.id.id7;
                        break;
                    case 3:
                        id = R.id.id8;
                        break;
                }
                break;
            case 2:
                switch (column) {
                    case 0:
                        id = R.id.id9;
                        break;
                    case 1:
                        id = R.id.id10;
                        break;
                    case 2:
                        id = R.id.id11;
                        break;
                    case 3:
                        id = R.id.id12;
                        break;
                }
                break;
            case 3:
                switch (column) {
                    case 0:
                        id = R.id.id13;
                        break;
                    case 1:
                        id = R.id.id14;
                        break;
                    case 2:
                        id = R.id.id15;
                        break;
                    case 3:
                        id = R.id.id16;
                        break;
                }
                break;
        }
        return id;
    }
    public String toString(int help, int score, int won){
        String ret = "";
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ret += "" + board[i][j].getId() + ",";
            }
        }
        boolean t = false;
        if(t){
            ret += help;
        } else {
            ret += help + "," + score + "," + won;
        }
        return ret;
    }
//	public void drawRow(int row){
//		for(int count = 0; count < 7; count++){
//			for(int column = 0;  column < 4; column++){
//				System.out.print(board[row][column].getLine(count));
//			}
//			System.out.println();
//		}
//	}
}
