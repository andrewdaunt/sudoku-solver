import React, { useState } from 'react';
import SudokuBoard from '../components/SudokuBoard';
import SubmitButton from '../components/SubmitButtons';

function SudokuSolver(){
    // Variable for managing the current board state
    const [board, setBoard] = useState('0'.repeat(81));

    // Resets board to blank
    function handleClickReset(){
        setBoard('0'.repeat(81));
    }

    // Updates board with solved state
    async function handleClickSolve(){
        // API call
        const solvedBoard = await getSolvedBoard();
        // If solvedBoard is valid, the board state will be updated
        /*
        if(solvedBoard !== board && solvedBoard.length === 81){
            setBoard(solvedBoard);
        } else{
            alert('Invalid Board')
            console.log('Invalid Board');
        }
        */
    };

    // Makes api call and returns solved board as a string
    async function getSolvedBoard(){
        try{
            const URL = await import.meta.env.VITE_API_ADDRESS;
            const response = await fetch(URL + '/api/solve', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ board: board })
            });

            if(!response.ok){
                throw new Error(`Error: ${response.status}`);
            }

            const json = await response.json();

            // Get board value row by row
            let result = '';
            for(let i = 1; i < 10; i++){
                result += json[String(i)];
            }

            console.log('Response: ' + result);
            return result;
        } catch(error){
            console.error(error.message);
            return board;
        }
    };

    // Updates board state when a box is changed
    function handleBoxChange(rowIndex, colIndex, value){
        // Get index from row/col
        const index = rowIndex * 9 + colIndex;

        // Seperate board string into list and update
        let newBoard = board.split('');
        newBoard[index] = value;

        setBoard(newBoard.join(''));
    };

    // Returns a 9x9 grid of text input and a button conatiner
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