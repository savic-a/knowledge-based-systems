import React from 'react';
import AddIcon from '@mui/icons-material/Add';

const FinancialGoal = () => (
  <div className="framed-container">
     <div className="goal-header">
      <div className='component-title'>Financial Goal</div>
      <div className="add-goal">
        <AddIcon className="add-goal-icon" />
        <span>Add goal</span>
      </div>
    </div>
    <p>Save $10,000</p>
    <p>Current: $4,500</p>
  </div>
);

export default FinancialGoal;
