package com.example;
import java.util.HashMap;

public class SudokuResponse {
    public static HashMap<String, String> getResponse(String board){
        HashMap<String, String> response = new HashMap<>();

        int stringPos = 0;
        for(int i = 1; i < 10; i++){
            String part = board.substring(stringPos, stringPos + 9);
            response.put(String.valueOf(i), part);
            stringPos += 9;
        }

        return response;
    }

}
