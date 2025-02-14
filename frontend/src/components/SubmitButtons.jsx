import React from 'react';
import styles from '../styles/SudokuSolver.module.css';

function SubmitButton({ isValidBoard, onClickSolve, onClickReset }){
    // returns two buttons, one for api call and another for board reset
    return(
         <div className={styles.buttonsContainer}>
            <button className={styles.button} onClick={onClickSolve}>Solve</button>
            <button className={styles.button} onClick={onClickReset}>Reset</button>
        </div>
    );
}

export default SubmitButton;