import React from "react";
import styles from '../styles/SudokuSolver.module.css';

function SudokuBox({ value, onChange }){
    return (
        <input 
            className={styles.SudokuBox}
            type='text' pattern='[1-9]{1}' 
            value={value} 
            onChange={onChange} 
        />
    ); 
}; 

export default SudokuBox;