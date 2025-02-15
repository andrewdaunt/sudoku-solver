package com.example;
import java.util.HashMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SudokuController {
    private final SudokuSolver solver = new SudokuSolver();

    // Allow cross origin requests
    @CrossOrigin(origins = "*")

    @PostMapping("/solve")
    public HashMap<String, String> solveSudoku(@RequestBody String request) {
        String board = request.substring(10, request.length() - 2);
        String finishedBoard = solver.solvePuzzle(board);
        
        return SudokuResponse.getResponse(finishedBoard); 
    }
}
