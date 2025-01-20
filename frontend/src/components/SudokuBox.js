import React from "react";
import styles from '../styles/SudokuSolver.module.css';

function SudokuBox({ rowIndex, colIndex, value, onChange }){
    function handleChange(e){
        const newValue = e.target.value;

        if(newValue == '' || /^[1-9]$/.test(newValue)){
            onChange(rowIndex, colIndex, newValue === '' ? '0' : newValue);
        }
    };

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