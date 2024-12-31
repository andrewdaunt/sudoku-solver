import React, { useState } from 'react';
import SudokuBoard from '../components/SudokuBoard';
import SubmitButton from '../components/SubmitButton';

function SudokuSolver(){
    const [board, setBoard] = useState(initialBoard());

    function initialBoard(){
        // string of 0's
    };

    function updateBoard(){
        // display solved
    }

    function handleClick(){
        // call api
        // if valid: update board
        // else: alert
    };

    function handleBoxChange(rowIndex, colIndex, value){
        // determine index from row/col and update value
    };

    return(
        <div>
            <SudokuBoard board={board} onBoxChange={handleBoxChange} />
            <SubmitButton onClick={handleClick} />
        </div>
    );
};

export default SudokuSolver;