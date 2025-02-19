# Sudoku Solver

## Description
A website for solving 9x9 Sudoku puzzles.

### Instructions
* Fill the known numbers into the Sudoku board and click solve to get a solution.
* Click the 'Reset' button to reset to a blank board

## How to Run
The application can be run locally with Docker using the following steps:

1. Download the repository.
```
git clone https://github.com/andrewdaunt/sudoku-solver.git
```
2. Navigate to the 'frontend' directory.
```
cd sudoku-solver/frontend
```
3. Create a .env file with the following text:
```
VITE_API_ADDRESS=http://localhost:8080
```
4. Navigate back to the project root.
```
cd ..
```
5. Build and run the container.
```
docker compose up --build
```
6. Navigate to [http://localhost:5173](http://localhost:5173).

## Examples
### Blank Board
![Blank Board](https://github.com/andrewdaunt/sudoku-solver/blob/main/Board.png?raw=true)

### Invalid Board Configuration 
![Invalid Board Unsolved](https://github.com/andrewdaunt/sudoku-solver/blob/main/Invalid1.png?raw=true)
![Invalid Board](https://github.com/andrewdaunt/sudoku-solver/blob/main/Invalid2.png?raw=true)

### Solved Board
![Valid Board Unsolved](https://github.com/andrewdaunt/sudoku-solver/blob/main/Solve1.png?raw=true)
![Solved Board](https://github.com/andrewdaunt/sudoku-solver/blob/main/Solve2.png?raw=true)

## Planned Features
* Add loading animation when waiting for a solution.
* Indicate invalid numbers in the event of an invalid board.

## Known Issues
* Attempting to solve a board with too few entries results in a timeout.
