package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SudokuController {
    private final SudokuSolver solver = new SudokuSolver();

    @PostMapping("/solve")
    public String solveSudoku(@RequestBody String board) {
        String finishedBoard = solver.solvePuzzle(board);
        return finishedBoard; 
    }
}
