import React, { useState } from 'react';
import SudokuBoard from '../components/SudokuBoard';
import SubmitButton from '../components/SubmitButtons';

function SudokuSolver(){
    const [board, setBoard] = useState('0'.repeat(81));

    // Resets board to blank
    function handleClickReset(){
        setBoard('0'.repeat(81));
    }

    // Updates board with solved state
    async function handleClickSolve(){
        // Makes api call and returns solved board as a string
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

                let result = '';
                for(let i  = 1; i < 10; i++){
                    result += json[String(i)];
                }
                console.log('Response: ' + result);
                return result;
            } catch(error){
                console.error(error.message);
            }
        };

        // If solvedBoard is valid, the board state will be updated
        const solvedBoard = await getSolvedBoard();
        console.log(solvedBoard.length)
        if(solvedBoard != board && solvedBoard.length == 81){
            setBoard(solvedBoard);
        } else{
            console.log('Invalid Board');
        }
    };

    // Updates board state when a box is changed
    function handleBoxChange(rowIndex, colIndex, value){
        const index = rowIndex * 9 + colIndex;
        let newBoard = board.split('');
        newBoard[index] = value;
        setBoard(newBoard.join(''));
    };

    return(
        <div>
            <SudokuBoard 
                board={board} 
                onBoxChange={handleBoxChange} 
            />
            <SubmitButton 
                onClickSolve={handleClickSolve} 
                onClickReset={handleClickReset} 
            />
        </div>
    );
};

export default SudokuSolver;