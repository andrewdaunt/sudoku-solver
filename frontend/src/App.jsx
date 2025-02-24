import React from 'react';
import './styles/app.css';
import SudokuSolver from './containers/SudokuSolver';
import SudokuHeader from './containers/SudokuHeader';
import SudokuFooter from './containers/SudokuFooter';

// App presentational component
function App(){
    return(
        <div>
            <div className='header'>
                <SudokuHeader />
            </div>
            <div className='main-container'>
                <div className='main'>
                    <SudokuSolver />
                </div>
            </div>
            <div className='footer'>
                <SudokuFooter />
            </div>
        </div>
    );
};

export default App;