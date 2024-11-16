package com.example;
import java.util.HashSet;

public class SudokuBox {
    static final int BOARD_DIMENSION = 9;

    HashSet<Integer> possibleValues;
    int row, column;

    public SudokuBox(int i, int j){
        row = i;
        column = j;
        possibleValues = new HashSet<>();
    }

    public SudokuBox(int i, int j, int val){
        row = i;
        column = j;
        possibleValues = new HashSet<>();
        possibleValues.add(val);
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public int numPossibleValues(){
        return possibleValues.size();
    }

    public static int[] getSquareIntervals(int row, int column){
        int[] intervals = new int[4];

        if(row < 3){
            intervals[0] = 0;
            intervals[1] = 3; 
        } else if(row < 6){
            intervals[0] = 3;
            intervals[1] = 6; 
        } else {
            intervals[0] = 6;
            intervals[1] = 9; 
        }

        if(column < 3){
            intervals[2] = 0;
            intervals[3] = 3; 
        } else if(column < 6){
            intervals[2] = 3;
            intervals[3] = 6; 
        } else {
            intervals[2] = 6;
            intervals[3] = 9; 
        }

        return intervals;
    }

    public void initializePossibleValues(int[][] board){
        for(int i = 1; i < 10; i++){
            possibleValues.add(i);
        }

        for(int i = 0; i < BOARD_DIMENSION; i++){
            if(board[row][i] != 0)
                possibleValues.remove(board[row][i]);

            if(board[i][column] != 0)
                possibleValues.remove(board[i][column]);
        }

        int[] intervals = getSquareIntervals(this.row, this.column); 
        for(int i = intervals[0]; i < intervals[1]; i++){
            for(int j = intervals[2]; j < intervals[3]; j++){
                if(board[i][j] != 0)
                possibleValues.remove(board[i][j]);
            }
        }
    }

    public int[] getPossibleValues(){
        int[] values = new int[possibleValues.size()];
        int index = 0;
        for(int i : possibleValues){
            values[index] = i;
            index++;
        }
        return values;
    }

    public void setPossibleValue(int val){
        possibleValues.add(val);
    }

    public boolean removePossibleValue(int val){
        if(possibleValues.size() == 1 && possibleValues.contains(val)){
            return false;
        }
        
        if(possibleValues.contains(val))
            possibleValues.remove(val);

        return true;
    }

    public boolean chooseValue(int val){
        if(possibleValues.contains(val)){
            possibleValues.clear();
            possibleValues.add(val);
            return true;
        }

        return false;
    }
}
