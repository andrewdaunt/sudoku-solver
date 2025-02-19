#Sudoku Solver

##Description
A website for solving 9x9 Sudoku puzzles.

###Instructions
*Fill in the known numbers to the Sudoku board and click solve to get a solution.
*Click the 'Reset' button to reset to a blank board

##How to Run
The application can be ran locally with Docker using the following command:
```
docker build github.com/andrewdaunt/sudoku-solver
```

##Examples
###Blank Board
![alt text](https://github.com/andrewdaunt/sudoku-solver/blob/main/Board.png?raw=true)

###Invalid Board Configuration 
![alt text](https://github.com/andrewdaunt/sudoku-solver/blob/main/Invalid1.png?raw=true)
![alt text](https://github.com/andrewdaunt/sudoku-solver/blob/main/Invalid2.png?raw=true)

###Solved Board
![alt text](https://github.com/andrewdaunt/sudoku-solver/blob/main/Solve1.png?raw=true)
![alt text](https://github.com/andrewdaunt/sudoku-solver/blob/main/Solve2.png?raw=true)

##Planned Features
*Add loading animation when waiting for solution
*Indicate invalid numbers in event of invalid board

##Known Issues
*Attempting to solve a board with too few entries results in a timeout