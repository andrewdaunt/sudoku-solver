class SudokuSolver{

    private static boolean verifyInitialBoard(int[][] board){
        return false;
    }

    private static SudokuBox[][] initializeSudokuBoard(int[][] board){
        SudokuBox[][] sudokuBoard = new SudokuBox[9][9];

        return sudokuBoard;
    }

    private static SudokuBox getBestBox(SudokuBox[][] board){
        SudokuBox bestBox = board[0][0];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(board[i][j].numPossibleValues() > 1 && board[i][j].numPossibleValues() < bestBox.numPossibleValues()){
                    bestBox = board[i][j];
                }
            }
        }
        return bestBox;
    }

    private static void tryValues(SudokuBox[][] board){
        SudokuBox box = getBestBox(board);
        int[] values = box.getPossibleValues();
        for(int val : values){
            board[box.getRow()][box.getColumn()].chooseValue(val);
        }
    }

    public static void main(String args[]){
        int[][] initialBoard = new int[9][9];

        if(!verifyInitialBoard(initialBoard)){
            System.out.println("Invalid Input");
            return;
        }
            
        SudokuBox[][] sudokuBoard = initializeSudokuBoard(initialBoard);
        while(true){
            tryValues(sudokuBoard);
        }
        
    }
}