package com.example;
import java.util.HashMap;

public class SudokuResponse {
    public static HashMap<String, String> getResponse(String board){
        HashMap<String, String> response = new HashMap<>();

        for(int i = 0; i < 9; i++){
            String part = board.substring(i, i + 9);
            response.put(String.valueOf(i), part);
        }

        return response;
    }

}
