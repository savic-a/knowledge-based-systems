import React, { useEffect, useState } from 'react';
import clientService from '../../services/ClientService';

const Budget = () => {
  const [budget, setBudget] = useState([]);

  useEffect(() => {
    const fetchBudget = async () => {
      try {
        const item = await clientService.getBudget();
        setBudget(item);
      } catch (error) {
        console.error('Failed to fetch budget:', error);
      }
    };

        fetchBudget();
    }, []);

  
  return (
    <div className="framed-container">
      <div className='component-title'>Budget</div>
      <span>$</span>
      <span>{budget.value}</span>
    </div>
  );
};

export default Budget;
