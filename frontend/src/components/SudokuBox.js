import React from "react";
import styles from '../styles/SudokuSolver.module.css';

function SudokuBox({ rowIndex, colIndex, value, onChange }){
    return (
        <input 
            className={styles.sudokuBox}
            type='text' pattern='[1-9]?'
            value={value == 0 ? '' : value} 
            onChange={(e) => onChange(rowIndex, colIndex, e.target.value == '' ? '0' : e.target.value)} 
        />
    ); 
}; 

export default SudokuBox;