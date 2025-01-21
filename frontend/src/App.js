import React from 'react';
import './styles/app.css';
import SudokuSolver from './containers/SudokuSolver';
import SudokuHeader from './containers/SudokuHeader';

// App presentational component
function App(){
    return(
        <div>
            <div className='header'>
                <SudokuHeader />
            </div>
            <div className='main'>
                <SudokuSolver />
            </div>
            <div className='footer'></div>
        </div>
    );
};

export default App;