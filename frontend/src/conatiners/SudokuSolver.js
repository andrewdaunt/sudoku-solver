import React, { useState } from 'react';
import SudokuBoard from '../components/SudokuBoard';
import SubmitButton from '../components/SubmitButton';

function SudokuSolver(){
    function handleClick(){
        
    }

    return(
        <>
            <SudokuBoard onClick={handleClick}/>
            <SubmitButton />
        </>
    );
}