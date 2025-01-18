import React, { useState } from 'react';
import SudokuBoard from '../components/SudokuBoard';
import SubmitButton from '../components/SubmitButton';

function SudokuSolver(){
    const [board, setBoard] = useState('0'.repeat(81));

    function handleClick(){
        async function getSolvedBoard(){
            try{
                const response = await fetch('http://localhost:8080/api/solve', {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ board: board.substring(0, 81) })
                });

                if(!response.ok){
                    throw new Error(`Error: ${response.status}`);
                }

                const json = await response.json();
                console.log(json);
                return json;
            } catch(error){
                console.error(error.message);
            }
        }

        const solvedBoard = getSolvedBoard();
        if(solvedBoard != board && solvedBoard.length == 81){
            setBoard(solvedBoard);
        }
    };

    function handleBoxChange(rowIndex, colIndex, value){
        const index = rowIndex * 9 + colIndex;
        let newBoard = board.split('');
        newBoard[index] = value;
        setBoard(newBoard.join(''));
    };

    return(
        <div>
            <SudokuBoard board={board} onBoxChange={handleBoxChange} />
            <SubmitButton onClick={handleClick} />
        </div>
    );
};

export default SudokuSolver;