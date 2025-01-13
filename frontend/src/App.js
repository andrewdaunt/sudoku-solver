import React from 'react';
import './styles/app.css';
import SudokuSolver from './containers/SudokuSolver';

function App(){
    return(
        <div className='main'>
            <SudokuSolver />
        </div>
    );
};

export default App;