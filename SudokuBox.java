import java.util.HashSet;

public class SudokuBox {
    int row, column;
    HashSet<Integer> possibleValues;

    public SudokuBox(int i, int j){
        this.row = i;
        this.column = j;
        this.possibleValues = new HashSet<>();
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

    public int[] getPossibleValues(){
        int[] values = new int[possibleValues.size()];
        int count = 0;
        for(int i : possibleValues){
            values[count] = i;
            count++;
        }
        return values;
    }

    public void chooseValue(int val){

    }

    private void updatePossibleValues(){
        
    }

    private void initializePossibleValues(){

    }
}
