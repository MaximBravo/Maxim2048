package maximbravo.com.Maxim2048;

import java.util.Random;

public class Matrix {
	public static Square[][] updatedBoard;
    private static boolean swiped = false;
	public Matrix(Square[][] board){
		updatedBoard = board;
	}
	
	public static boolean update(int direction){
		switch(direction){
			case 0:
				up();
				break;
			case 1:
				right();
				break;
			case 2:
				down();
				break;
			case 3:
				left();
				break;
		}
        if(swiped) {
            swiped = false;
            return addRandomSquare();
        } else {
            return true;
        }
		
	}
    public static boolean getSwiped(){
        return swiped;
    }
	public static Square[][] getUpdatedBoard(){
		//swiped = true;
        return updatedBoard;
	}
	
	public static boolean addRandomSquare(){
		int[] pool = {2,2,2,2,2,4,2,2,2,2};
		Random rand = new Random();
		int squarePoolId = rand.nextInt(10 - 1) + 1;
		int squareIdToPut = pool[squarePoolId];
		int emptySpaceCount = getEmptySpaceCount();


		if(emptySpaceCount <= 1){
			placeNewSquareIn(0, squareIdToPut);
			if(emptySpaceCount == 0 && swiped == false){
				return false;
			}
			return true;
		}
		int randomNumPosition = rand.nextInt((emptySpaceCount-1));
		placeNewSquareIn(randomNumPosition, squareIdToPut);
		return true;
	}
	public static void placeNewSquareIn(int position, int id){
		int count = 0;
		boolean b = false;
		for(int row = 0; row < 4; row++){
			for(int column = 0; column < 4; column++){
				if(updatedBoard[row][column].getId() == 0){
					if(count == position && updatedBoard[row][column].getCanAdd() == true){
						updatedBoard[row][column] = new Square(id, true);
						b = true;
						break;
					}
					count++;
				}
			}
			if(b){
				break;
			}
		}
	}
	public static int getEmptySpaceCount(){
		int result = 0;
		for(int row = 0; row < 4; row++){
			for(int column = 0; column < 4; column++){
				if(updatedBoard[row][column].getId() == 0){
					result++;
				}
			}
		}
		return result;
	}
	
	public static void up(){
		for(int column = 0; column < 4; column++){
			swipeUp(column);
		}
		
	}
	public static void right(){
		for(int row = 0; row < 4; row++){
			swipeRight(row);
		}
    }
	public static void down(){
		for(int column = 0; column < 4; column++){
			swipeDown(column);
		}
	}
	public static void left(){
		for(int row = 0; row < 4; row++){
			swipeLeft(row);
		}
	}


	public static void swipe(int startRow, int startColumn, int endRow, int endColumn, int direction, String before){
		for(int row = startRow; row <= endRow; row++){
			for(int column = startColumn; column <= endColumn; column++){
				Square current = updatedBoard[row][column];
				Square next;
				switch(direction){
					case 0:
						next = updatedBoard[row+1][column];
						break;
					case 1:
						next = updatedBoard[row][column-1];
						break;
					case 2:
						next = updatedBoard[row-1][column];
						break;
					case 3:
						next = updatedBoard[row][column+1];
						break;
					default:
						next = current;
				}
				boolean isCurrentPerfect = false;
				boolean isNextPerfect = false;
				boolean isCurrentPrime = isPrime(current.getId());
				boolean isNextPrime = isPrime(next.getId());
				boolean isCurrentEven = false;
				boolean isNextEven = false;
				double currentSquare = Math.sqrt((double) current.getId());
				double nextSquare = Math.sqrt((double) current.getId());
				if(currentSquare*currentSquare == current.getId()) isCurrentPerfect = true;
				if(nextSquare*nextSquare == next.getId()) isNextPerfect = true;
				if(current.getId()%2 == 0) isCurrentEven = true;
				if(next.getId()%2 == 0) isNextEven = true;

				if(isCurrentPerfect && isNextPerfect){

				}
			}
		}
	}

	public static boolean isPrime(int n) {
		//check if n is a multiple of 2
		if (n%2==0) return false;
		//if not, then just check the odds
		for(int i=3;i*i<=n;i+=2) {
			if(n%i==0)
				return false;
		}
		return true;
	}
	public static void swipeUp(int column){
		int count = 0;
        int first = 0;
        int second = 0;
        for(int row = 0; row < 4; row++){
            first += (updatedBoard[row][column].getId()*(10*(row+1)))/10;
        }
		for(int row = 0; row < 3; row++){
			Square current = updatedBoard[row][column];
			Square downSquare = updatedBoard[row+1][column];
			if(current.getId() == 512 && downSquare.getId() == 512){
				MainActivity.addToHelpLeft(3);
			}
			if(current.getId() == downSquare.getId() && current.getId() != 0){
				updatedBoard[row][column] = new Square(current.getId()*2);
                MainActivity.addToScore(current.getId()*2);
				for(int k = row+1; k < 3; k++){
                    Square resetSquare = updatedBoard[k+1][column];
                    resetSquare.setJustAdded(false);
					updatedBoard[k][column] = resetSquare;
				}
				updatedBoard[3][column] = new Square(0);
                swiped = true;
			} else if(current.getId() == 0 && count < 4){
				if(current.getId() == 0 && downSquare.getId() != 0){
					swiped = true;
				}
				for(int k = row; k < 3; k++){
                    Square resetSquare = updatedBoard[k+1][column];
                    resetSquare.setJustAdded(false);
                    if(resetSquare.getId() > 0){
                        swiped = true;
                    }
					updatedBoard[k][column] = resetSquare;
				}
				updatedBoard[3][column] = new Square(0);
				row--;
				count++;
                //swiped = true;
			} else if(downSquare.getId() == 0 && count < 4 && row < 3){
				if(current.getId() == 0 && downSquare.getId() != 0){
					swiped = true;
				}
				for(int k = row+1; k < 3; k++){
                    Square resetSquare = updatedBoard[k+1][column];
                    resetSquare.setJustAdded(false);
					updatedBoard[k][column] = resetSquare;
				}
				updatedBoard[3][column] = new Square(0);
				row--;
				count++;
                //swiped = true;
			}
			if(current.getId() == 0 && downSquare.getId() != 0){
				swiped = true;
			}

		}
        for(int row = 0; row < 4; row++){
            second += (updatedBoard[row][column].getId()*(10*(row+1)))/10;
        }
        if(first != second){
            swiped = true;
        }
	}

	
	public static void swipeDown(int column){
		int count = 0;
        int first = 0;
        int second = 0;
        for(int row = 0; row < 4; row++){
            first += (updatedBoard[row][column].getId()*(10*(row+1)))/10;
        }
		for(int row = 3; row > 0; row--){
			Square current = updatedBoard[row][column];
			Square upSquare = updatedBoard[row-1][column];
			if(current.getId() == 512 && upSquare.getId() == 512){
				MainActivity.addToHelpLeft(3);
			}
			if(current.getId() == upSquare.getId() && current.getId() != 0){
				updatedBoard[row][column] = new Square(current.getId()*2);
                MainActivity.addToScore(current.getId()*2);
				for(int k = row-1; k > 0; k--){
                    Square resetSquare = updatedBoard[k-1][column];
                    resetSquare.setJustAdded(false);
					updatedBoard[k][column] = resetSquare;
				}
				updatedBoard[0][column] = new Square(0);
                swiped = true;
			} else if(current.getId() == 0 && count < 4){
				if(current.getId() == 0 && upSquare.getId() != 0){
					swiped = true;
				}
				for(int k = row; k > 0; k--){
                    Square resetSquare = updatedBoard[k-1][column];
                    resetSquare.setJustAdded(false);
                    if(resetSquare.getId() > 0){
                        swiped = true;
                    }
					updatedBoard[k][column] = resetSquare;
				}
				updatedBoard[0][column] = new Square(0);
				row++;
				count++;
                //swiped = true;
			} else if(upSquare.getId() == 0 && count < 4 && row > 2){
				if(current.getId() == 0 && upSquare.getId() != 0){
					swiped = true;
				}
				for(int k = row-1; k > 0; k--){
                    Square resetSquare = updatedBoard[k-1][column];
                    resetSquare.setJustAdded(false);
					updatedBoard[k][column] = resetSquare;
				}
				updatedBoard[0][column] = new Square(0);
				row++;
				count++;
                //swiped = true;
			}
			if(current.getId() == 0 && upSquare.getId() != 0){
				swiped = true;
			}
		}
        for(int row = 0; row < 4; row++){
            second += (updatedBoard[row][column].getId()*(10*(row+1)))/10;
        }
        if(first != second){
            swiped = true;
        }
	}
	public static void swipeLeft(int row){
		int count = 0;
        int first = 0;
        int second = 0;
        for(int column = 0; column < 4; column++){
            first += (updatedBoard[row][column].getId()*(10*(column+1)))/10;
        }
		for(int column = 0; column < 3; column++){
			Square current = updatedBoard[row][column];
			Square rightSquare = updatedBoard[row][column+1];
			if(current.getId() == 512 && rightSquare.getId() == 512){
				MainActivity.addToHelpLeft(3);
			}
			if(current.getId() == rightSquare.getId() && current.getId() != 0){
				updatedBoard[row][column] = new Square(current.getId()*2);
                MainActivity.addToScore(current.getId()*2);
				for(int k = column+1; k < 3; k++){
                    Square resetSquare = updatedBoard[row][k+1];
                    resetSquare.setJustAdded(false);
					updatedBoard[row][k] = resetSquare;
				}
				updatedBoard[row][3] = new Square(0);
                swiped = true;
			} else if(current.getId() == 0 && count < 4){
				if(current.getId() == 0 && rightSquare.getId() != 0){
					swiped = true;
				}
				for(int k = column; k < 3; k++){
                    Square resetSquare = updatedBoard[row][k+1];
                    resetSquare.setJustAdded(false);
                    if(resetSquare.getId() > 0){
                        swiped = true;
                    }
					updatedBoard[row][k] = resetSquare;
				}
				updatedBoard[row][3] = new Square(0);
				column--;
				count++;
                //swiped = true;
			} else if(rightSquare.getId() == 0 && count < 4 && column < 2){
				if(current.getId() == 0 && rightSquare.getId() != 0){
					swiped = true;
				}
				for(int k = column+1; k < 3; k++){
                    Square resetSquare = updatedBoard[row][k+1];
                    resetSquare.setJustAdded(false);
					updatedBoard[row][k] = resetSquare;
				}
				updatedBoard[row][3] = new Square(0);
				column--;
				count++;
                //swiped = true;
			}
			if(current.getId() == 0 && rightSquare.getId() != 0){
				swiped = true;
			}
		}
        for(int column = 0; column < 4; column++){
            second += (updatedBoard[row][column].getId()*(10*(column+1)))/10;
        }
        if(first != second){
            swiped = true;
        }
	}
	
	public static void swipeRight(int row){
		int count = 0;
        int first = 0;
        int second = 0;
        for(int column = 0; column < 4; column++){
            first += (updatedBoard[row][column].getId()*(10*(column+1)))/10;
        }
		for(int column = 3; column > 0; column--){
			Square current = updatedBoard[row][column];
			Square leftSquare = updatedBoard[row][column-1];
			if(current.getId() == 512 && leftSquare.getId() == 512){
				MainActivity.addToHelpLeft(3);
			}
			if(current.getId() == leftSquare.getId() && current.getId() != 0){
				updatedBoard[row][column] = new Square(current.getId()*2);
                MainActivity.addToScore(current.getId()*2);
				for(int k = column-1; k > 0; k--){
                    Square resetSquare = updatedBoard[row][k-1];
                    resetSquare.setJustAdded(false);
					updatedBoard[row][k] = resetSquare;
				}
				updatedBoard[row][0] = new Square(0);

			} else if(current.getId() == 0 && count < 4){

				for(int k = column; k > 0; k--){
                    Square resetSquare = updatedBoard[row][k-1];
                    resetSquare.setJustAdded(false);
					updatedBoard[row][k] = resetSquare;
				}
				updatedBoard[row][0] = new Square(0);
				column++;
				count++;
			} else if(leftSquare.getId() == 0 && count < 4 && column > 2){
				for(int k = column-1; k > 0; k--){
                    Square resetSquare = updatedBoard[row][k-1];
                    resetSquare.setJustAdded(false);
					updatedBoard[row][k] = resetSquare;
				}
				updatedBoard[row][0] = new Square(0);
				column++;
				count++;
			}

		}
        for(int column = 0; column < 4; column++){
            second += (updatedBoard[row][column].getId()*(10*(column+1)))/10;
        }
        if(first != second){
            swiped = true;
        }
	}
}
