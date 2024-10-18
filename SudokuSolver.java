import java.util.HashMap;

class SudokuSolver{
    static final int BOARD_DIMENSION = 9;

    private static boolean verifyInitialBoard(int[][] board){
        int minValue = 0;
        int maxValue = 10;

        for(int i = 0; i < BOARD_DIMENSION; i++){
            for(int j = 0; j < BOARD_DIMENSION; j++){
                if(board[i][j] < minValue || board[i][j] > maxValue)
                    return false;
            }
        }

        return true;
    }

    private static SudokuBox[][] initializeSudokuBoard(int[][] board){
        SudokuBox[][] sudokuBoard = new SudokuBox[BOARD_DIMENSION][BOARD_DIMENSION];
        int emptyBox = 0;

        for(int i = 0; i < BOARD_DIMENSION; i++){
            for(int j = 0; j < BOARD_DIMENSION; j++){
                if(board[i][j] != emptyBox){
                    SudokuBox currentBox =  new SudokuBox(i, j, board[i][j]);
                    sudokuBoard[i][j] = currentBox;
                } else{
                    SudokuBox currentBox =  new SudokuBox(i, j);
                    currentBox.initializePossibleValues(board);
                    sudokuBoard[i][j] = currentBox;
                }
            }
        }

        return sudokuBoard;
    }

    private static SudokuBox getBestBox(SudokuBox[][] board){
        SudokuBox bestBox = null;
        for(int i = 0; i < BOARD_DIMENSION; i++){
            for(int j = 0; j < BOARD_DIMENSION; j++){
                if(board[i][j].numPossibleValues() <= 1)
                    continue;

                if(bestBox == null){
                    bestBox = board[i][j];
                    continue;
                }

                if(board[i][j].numPossibleValues() < bestBox.numPossibleValues()){
                    bestBox = board[i][j];
                }
            }
        }
        return bestBox;
    }
 
    private static boolean updatePossibleValue(SudokuBox[][] board, int row, int column, int val){
        for(int i = 0; i < BOARD_DIMENSION; i++){


            SudokuBox rowBox = board[row][i];            
            SudokuBox columnBox = board[i][column];
            if((i != row && !columnBox.removePossibleValue(val)) || (i != column && !rowBox.removePossibleValue(val))){
                return false;
            }
        }

        int[] intervals = SudokuBox.getSquareIntervals(row, column); 
        int rowStart = intervals[0];
        int rowEnd = intervals[1];
        int columnStart = intervals[2];
        int columnEnd = intervals[3];
        
        for(int i = rowStart; i < rowEnd; i++){
            for(int j = columnStart; j < columnEnd; j++){
                SudokuBox box = board[i][j];
                if(i != row && j != column && !box.removePossibleValue(val)){
                    return false;
                }    
            }
        }
        return true;
    }

    private static boolean isFinishedBoard(SudokuBox[][] board){
        HashMap<Integer, Integer> numCounts = new HashMap<>();
        for(int i = 0; i < BOARD_DIMENSION; i++){
            for(int j = 0; j < BOARD_DIMENSION; j++){
                int val = board[i][j].getPossibleValues()[0];
                numCounts.put(val, numCounts.getOrDefault(val, 0) + 1);
            }
        }

        for(int i = 1; i < 10; i++){
            if(numCounts.get(i) != 9)
                return false;
        }

        return true;
    }

    private static SudokuBox[][] copySudokuBoard(SudokuBox[][] board){
        SudokuBox[][] copyBoard = new SudokuBox[BOARD_DIMENSION][BOARD_DIMENSION];
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                copyBoard[i][j] = copySudokuBox(board, i, j);
            }
        }
        return copyBoard;
    }

    private static SudokuBox copySudokuBox(SudokuBox[][] board, int row, int column){
        int[] possibleValues = board[row][column].getPossibleValues();
        SudokuBox copy = new SudokuBox(row, column);
        for(int val : possibleValues){
            copy.setPossibleValue(val);
        }
        return copy;
    }

    private static void solve(SudokuBox[][] board, SudokuBox box) {
        if (box == null) return;
    
        int[] values = box.getPossibleValues();
        for (int val : values) {
            SudokuBox[][] copyBoard = copySudokuBoard(board);
            if (copyBoard[box.getRow()][box.getColumn()].chooseValue(val)) {

                if (!updatePossibleValue(copyBoard, box.getRow(), box.getColumn(), val)) {
                    continue;
                }
                
                if (isFinishedBoard(copyBoard)) {
                    displayFinishedBoard(copyBoard);
                    System.exit(0);
                }

                SudokuBox nextBox = getBestBox(copyBoard);
                solve(copyBoard, nextBox);
            }
        }
    }
    

    private static void displayFinishedBoard(SudokuBox[][] board){
        for(int i = 0; i < BOARD_DIMENSION; i++){
            for(int j = 0; j < BOARD_DIMENSION; j++){
                SudokuBox box = board[i][j];
                int[] val = box.getPossibleValues();
                System.out.print(val[0] + " | ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]){
        //int[][] initialBoard = new int[BOARD_DIMENSION][BOARD_DIMENSION];
        int[][] initialBoard = {
            {4, 1, 0, 0, 6, 0, 0, 7, 8}, 
            {7, 0, 3, 5, 0, 1, 4, 2, 0}, 
            {0, 0, 8, 4, 7, 3, 0, 6, 0}, 
            {0, 5, 0, 0, 9, 4, 8, 3, 0}, 
            {3, 9, 0, 0, 1, 0, 7, 0, 0}, 
            {2, 8, 4, 3, 0, 0, 0, 0, 0}, 
            {6, 0, 0, 0, 0, 0, 0, 8, 0}, 
            {0, 0, 1, 9, 4, 0, 0, 0, 0},
            {0, 4, 9, 0, 2, 8, 0, 0, 0}
        };
        
        if(!verifyInitialBoard(initialBoard)){
            System.out.println("Invalid Input");
            return;
        }
        
        SudokuBox[][] sudokuBoard = initializeSudokuBoard(initialBoard);
        SudokuBox box = getBestBox(sudokuBoard);
        solve(sudokuBoard, box);
    }
}