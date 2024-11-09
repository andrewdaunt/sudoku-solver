package com.example.service;
import com.example.model.SudokuBox;
import java.util.HashSet;

public class SudokuSolver{
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

    private static boolean verifyRowsAndColumns(SudokuBox[][] board){
        for(int i = 0; i < BOARD_DIMENSION; i++){
            HashSet<Integer> rowCount = new HashSet<>();
            HashSet<Integer> columnCount = new HashSet<>();

            for(int j = 0; j < BOARD_DIMENSION; j++){
                int[] rowPossibleValues = board[i][j].getPossibleValues();
                int[] columnPossibleValues = board[j][i].getPossibleValues();
                if(rowPossibleValues.length > 1 || columnPossibleValues.length > 1)
                    return false;

                int rowVal = rowPossibleValues[0];
                int columnVal = columnPossibleValues[0];
                if(rowCount.contains(rowVal) || columnCount.contains(columnVal))
                    return false;

                rowCount.add(rowVal);
                columnCount.add(columnVal);
            }
        }
        return true;
    }

    private static boolean verifySquares(SudokuBox[][] board){
        for(int row = 0; row < 12; row += 3){
            for(int column = 0; column < 12; column += 3){
                int[] intervals = SudokuBox.getSquareIntervals(row, column); 
                int rowStart = intervals[0];
                int rowEnd = intervals[1];
                int columnStart = intervals[2];
                int columnEnd = intervals[3];

                for(int i = rowStart; i < rowEnd; i++){
                    HashSet<Integer> squareCount = new HashSet<>();
                    for(int j = columnStart; j < columnEnd; j++){
                        int[] possibleValues = board[i][j].getPossibleValues();
                        if(possibleValues.length > 1)
                            return false;

                        int val = possibleValues[0];
                        if(squareCount.contains(val))
                            return false;

                        squareCount.add(val);
                    }
                }
            }
        }
        return true;
    }

    private static boolean isFinishedBoard(SudokuBox[][] board){
        if(!verifyRowsAndColumns(board) || !verifySquares(board))
            return false;

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
                    displayFinishedBoard(copyBoard);    // For testing
                    getBoardString(copyBoard);
                    // SEND FINISHED BOARD STRING
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

    private static int[][] getInitialBoard(char[] inputArray){
        int[][] board = new int[BOARD_DIMENSION][BOARD_DIMENSION];
        
        int index = 0;
        for(int i = 0; i < BOARD_DIMENSION; i++){
            for(int j = 0; j < BOARD_DIMENSION; j++){
                board[i][j] = Integer.valueOf(inputArray[index]);
                index++;
            }
        }

        return board;
    }   

    private static String getBoardString(SudokuBox[][] board){
        String finishedBoard = "";

        for(int i = 0; i < BOARD_DIMENSION; i++){
            for(int j = 0; j < BOARD_DIMENSION; j++){
                SudokuBox box = board[i][j];
                int[] val = box.getPossibleValues();
                finishedBoard += val[0];
            }
        }

        return finishedBoard;
    }

    public static void main(String args[]){
        char[] inputArray = args[0].toCharArray();

        if(inputArray.length != 81){
            // SEND UNSOLVABLE INDICATOR
            return;
        }

        int[][] initialBoard = getInitialBoard(inputArray);

        if(!verifyInitialBoard(initialBoard)){
            // SEND UNSOLVABLE INDICATOR
            return;
        }
        
        SudokuBox[][] sudokuBoard = initializeSudokuBoard(initialBoard);
        SudokuBox box = getBestBox(sudokuBoard);
        solve(sudokuBoard, box);
        // SEND UNSOLVABLE INDICATOR
    }
}