import React from 'react';
import styles from '../styles/SudokuSolver.module.css';

function SubmitButton({ onClick }){
    return <button className={styles.submitButton} onClick={onClick}>Solve</button>
}

export default SubmitButton;