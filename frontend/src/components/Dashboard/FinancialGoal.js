import React from 'react';
import LinearProgress from '@mui/material/LinearProgress';
import AddIcon from '@mui/icons-material/Add';

const FinancialGoal = () => {

    const target = 10000;
    const current = 4500;
    const progress = (current / target) * 100;

    return (
        <div className="framed-container">
            <div className="goal-header">
                <div className='component-title'>Financial Goal</div>
                    <div className="add-goal">
                        <AddIcon className="add-goal-icon" />
                        <span>Add goal</span>
                    </div>
            </div>
            <div className="goal-details">
                <div>
                    <p style={{fontWeight: "500"}}>New car</p>
                    <p style={{color: "gray", fontSize: "12px"}}>Target: $10,000</p>
                    <p>Saving: $4,500</p>
                </div>
                <LinearProgress variant="determinate" value={progress} sx={{ bgcolor: '#f0f0f0', '& .MuiLinearProgress-bar': { bgcolor: '#ff5722' } }}/>
            </div>
        </div>
    );
}

export default FinancialGoal;
