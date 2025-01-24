import React from 'react';
import styles from '../styles/SudokuFooter.module.css';

function ProfileLink(){
    // return anchor element lineked to github profile
    return <a className={styles.profileLink} href='https://github.com/andrewdaunt?tab=repositories'>GitHub</a>;
}

export default ProfileLink;