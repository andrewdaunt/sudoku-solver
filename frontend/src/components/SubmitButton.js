import React from 'react';
import styles from '../styles/SubmitButton';

function SubmitButton({ onClick }){
    return <button style={styles} onClick={onClick}>Solve</button>
}

export default SubmitButton;