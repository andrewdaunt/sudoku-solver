package com.example;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SudokuController {
    private final SudokuSolver solver = new SudokuSolver();

    @CrossOrigin(origins = "http://localhost:3000")

    @PostMapping("/solve")
    public String solveSudoku(@RequestBody String request) {
        String board = request.substring(1, request.length() - 1);
        System.out.println(board);
        String finishedBoard = solver.solvePuzzle(board);
        System.out.println("RETURNING");
        return finishedBoard; 
    }
}
