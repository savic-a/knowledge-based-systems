import React, { useEffect, useState } from 'react';
import clientService from '../../services/ClientService';
import { Button, TextField } from '@mui/material';

const Budget = () => {
  const [budget, setBudget] = useState(null);
  const [newBudgetValue, setNewBudgetValue] = useState('');

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

  const handleNewBudgetChange = (event) => {
    setNewBudgetValue(event.target.value);
  };

  const handleAddBudget = async () => {
    try {
      const newBudget = await clientService.addBudget({ value: newBudgetValue });
      setBudget(newBudget);
      setNewBudgetValue('');
    } catch (error) {
      console.error('Failed to add budget:', error);
    }
  };

  return (
    <div className="framed-container">
      <div className="component-title">Budget</div>
      {budget ? (
        <>
          <span>$</span>
          <span>{budget.value}</span>
        </>
      ) : (
        <>
          <TextField
            type="number" 
            value={newBudgetValue} 
            onChange={handleNewBudgetChange} 
            placeholder="Enter budget value"
            fullWidth
          />
          <Button
            variant="contained"
            color="primary"
            onClick={handleAddBudget} 
            disabled={!newBudgetValue}
            sx={{ mt: 2 }}
          >
            Add Budget
          </Button>
        </>
      )}
    </div>
  );
};

export default Budget;
