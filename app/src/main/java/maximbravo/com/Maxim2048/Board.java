package maximbravo.com.Maxim2048;

public class Board {
	private Square[][] board = new Square[4][4];
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
	public void initializeBoard(){
		for(int row = 0; row < 4; row++){
			Square[] s = new Square[4];
			for(int column = 0; column < 4; column++){
				s[column] = new Square(0);
			}
			board[row] = s;
		}
		
		Matrix m = new Matrix(board);
		m.addRandomSquare();
		m.addRandomSquare();
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
//	public void drawRow(int row){
//		for(int count = 0; count < 7; count++){
//			for(int column = 0;  column < 4; column++){
//				System.out.print(board[row][column].getLine(count));
//			}
//			System.out.println();
//		}
//	}
}
