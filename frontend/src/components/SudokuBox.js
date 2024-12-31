import React from "react";

function SudokuBox({ value, onChange }){
    return (
        <input 
            className='sudoku-box' 
            type='text' pattern='[1-9]{1}' 
            value={value} 
            onChange={onChange} 
        />
    );
};

export default SudokuBox;