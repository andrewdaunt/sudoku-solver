import React from 'react';
import styles from '../styles/SudokuSolver.module.css';

function SubmitButton({ isValidBoard, onClickSolve, onClickReset }){
    return(
         <div className={styles.buttonsContainer}>
            <button disabled={isValidBoard == false} className={styles.button} onClick={onClickSolve}>Solve</button>
            <button className={styles.button} onClick={onClickReset}>Reset</button>
        </div>
    );
}

export default SubmitButton;