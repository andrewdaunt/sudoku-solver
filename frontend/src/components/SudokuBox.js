import React from "react";
import styles from '../styles/SudokuSolver.module.css';

function SudokuBox({ rowIndex, colIndex, value, onChange }){
    function handleChange(e){
        const newValue = e.target.value;

        // If newValue is blank or a digit 1-9, then call function to update value
        if(newValue === '' || /^[1-9]$/.test(newValue)){
            onChange(rowIndex, colIndex, newValue === '' ? '0' : newValue);
        }
    };

    // Return an input box for a single character 1-9 or an empty box
    return (
        <input 
            className={styles.sudokuBox}
            type='text' pattern='[1-9]?'
            maxLength="1"
            value={value == 0 ? '' : value} 
            onChange={handleChange} 
        />
    ); 
}; 

export default SudokuBox;