
public class Queens {
	public static final int BOARD_SIZE = 8;
	public static final int EMPTY = 0;
	public static final int QUEEN = 1;
	
	private int board[][];
	
	public static void main(String[] args) {
		Queens q = new Queens();
		q.placeQueens(0);
		q.displayBoard();
	}
	
	public Queens(){
		this.board = new int[BOARD_SIZE][BOARD_SIZE];
	}
	
	public void clearBoard(){
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				removeQueen(i, j);
			}	
		}
	}
	
	public void displayBoard(){
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				System.out.print("[");
				if(board[i][j] == QUEEN) System.out.print("Q");
				else if(!this.isUnderAttack(i, j)) System.out.print("x");
				else System.out.print(" ");
				System.out.print("]");
			}
			System.out.println();
		}
	}
	
	public boolean placeQueens(int column){
		this.displayBoard();
		System.out.println();
		if (column >= BOARD_SIZE){
			return true;
		} else {
			boolean queenPlaced = false;
			int row = 0;
			
			while ( !queenPlaced && (row < BOARD_SIZE) ) {
				if (isUnderAttack(row,column)) {
					++row;
				} else {
					setQueen(row, column);
					queenPlaced = placeQueens(column+1);
					
					if(!queenPlaced) {
						// BACKTRACK
						System.out.println("Backtrack");
						removeQueen(row,column);
						++row;
					}
				}
			}
			
			return queenPlaced;
		}
	}

	private void removeQueen(int row, int column) {
		this.board[row][column] = EMPTY;
	}

	private void setQueen(int row, int column) {
		this.board[row][column] = QUEEN;
	}

	private boolean isUnderAttack(int row, int column) {
		// Checking horizontal and vertical
		for(int i = 0; i < BOARD_SIZE; i++){
			if ( (this.board[row][i] == QUEEN) || (this.board[i][column] == QUEEN)) return true;
		}
		
		// Checking diagonal (down - right)
		for(int i = 0; row+i < BOARD_SIZE && column+i < BOARD_SIZE; i++){
			int tile = this.board[row+i][column+i];
			if ( tile == QUEEN ) return true;
		}
		
		// Checking diagonal (up - left)
		for(int i = 0; row-i >= 0 && column-i >= 0; i++){
			if ( this.board[row-i][column-i] == QUEEN ) return true;
		}
		
		// Checking diagonal (down - left)
		for(int i = 0; row+i < BOARD_SIZE && column-i >= 0; i++){
			if ( this.board[row+i][column-i] == QUEEN ) return true;
		}
		
		// Checking diagonal (up - right)
		for(int i = 0; row-i >= 0 && column+i < BOARD_SIZE; i++){
			if ( this.board[row-i][column+i] == QUEEN ) return true;
		}

		return false;
	}	
}
