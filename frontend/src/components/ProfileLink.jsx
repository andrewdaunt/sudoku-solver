import React from 'react';
import styles from '../styles/SudokuFooter.module.css';

function ProfileLink(){
    // return profile links
    return(
        <div className={styles.linkContainer}>
            <a className={styles.profileLink} href='https://github.com/andrewdaunt?tab=repositories'>GitHub</a>
            <a className={styles.profileLink} href='https://www.linkedin.com/in/andrew-daunt-382247311'>LinkedIn</a>
        </div>
    );
}

export default ProfileLink;