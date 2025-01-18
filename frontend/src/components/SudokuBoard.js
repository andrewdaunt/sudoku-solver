import React from 'react';
import SudokuBox from './SudokuBox';
import styles from '../styles/SudokuSolver.module.css';;

function SudokuBoard({ board, onBoxChange }) {
    const gridSize = 9;

    function getIndex(rowIndex, colIndex){
        return rowIndex * gridSize  + colIndex;
    }

    return(
        <div className={styles.sudokuBoard}>
            {Array.from({ length: gridSize }).map((_, rowIndex) => (
                <div className={styles.sudokuBoardRow}>
                    {Array.from({ length: gridSize }).map((_, colIndex) => (
                        <SudokuBox
                            key={`${rowIndex}x${colIndex}`}
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