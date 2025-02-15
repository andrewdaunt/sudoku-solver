import React from 'react';
import SudokuBox from './SudokuBox';
import styles from '../styles/SudokuSolver.module.css';;

function SudokuBoard({ board, onBoxChange }) {
    const gridSize = 9;

    // Get flat index from grid
    function getIndex(rowIndex, colIndex){
        return rowIndex * gridSize  + colIndex;
    }

    // returns a 9x9 grid of input boxes
    return(
        <div className={styles.sudokuBoard}>
            {Array.from({ length: gridSize }).map((_, rowIndex) => (
                <div className={styles.sudokuBoardRow} key={rowIndex}>
                    {Array.from({ length: gridSize }).map((_, colIndex) => (
                        <SudokuBox
                            key={getIndex(rowIndex, colIndex)}
                            rowIndex={rowIndex}
                            colIndex={colIndex}
                            className={styles.sudokuBox}
                            value={board[getIndex(rowIndex, colIndex)]} 
                            onChange={onBoxChange}
                        />
                    ))}
                </div>
            ))}
        </div>
    );
};

export default SudokuBoard;