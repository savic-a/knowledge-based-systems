import React, { useEffect, useState } from 'react';
import LinearProgress from '@mui/material/LinearProgress';
import AddIcon from '@mui/icons-material/Add';
import clientService from '../../services/ClientService';

const FinancialGoal = ({ clientId }) => {
    const [financialGoal, setFinancialGoal] = useState([]);

    useEffect(() => {
        const fetchCreditCards = async () => {
          try {
                const goal = await clientService.getFinancialGoal(clientId);
                console.log(goal)
                setFinancialGoal(goal);
            } catch (error) {
                console.error('Failed to fetch credit cards:', error);
            }
        };
    
        fetchCreditCards();
    }, [clientId]);

    const target = financialGoal.targetValue;
    const current = financialGoal.currentBalance;
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
                    <p style={{fontWeight: "500"}}>{financialGoal.name}</p>
                    <span style={{color: "gray", fontSize: "12px"}}>Target: $</span>
                    <span style={{color: "gray", fontSize: "12px"}}>{financialGoal.targetValue}</span>
                    <div style={{marginBottom: "20px"}}></div>
                    <span>Saving: $</span>
                    <span>{financialGoal.currentBalance}</span>
                </div>
                <LinearProgress style={{marginTop: "10px"}} variant="determinate" value={progress} sx={{ bgcolor: '#f0f0f0', '& .MuiLinearProgress-bar': { bgcolor: '#ff5722' } }}/>
            </div>
        </div>
    );
}

export default FinancialGoal;
