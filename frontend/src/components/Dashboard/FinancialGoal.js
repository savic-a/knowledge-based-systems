import React, { useEffect, useState } from 'react';
import LinearProgress from '@mui/material/LinearProgress';
import AddIcon from '@mui/icons-material/Add';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import TextField from '@mui/material/TextField';
import clientService from '../../services/ClientService';

const FinancialGoal = () => {
    const [financialGoal, setFinancialGoal] = useState(null);
    const [open, setOpen] = useState(false);
    const [goalName, setGoalName] = useState('');
    const [goalDescription, setGoalDescription] = useState('');
    const [goalTargetValue, setGoalTargetValue] = useState('');
    const [goalTargetDate, setGoalTargetDate] = useState('');

    useEffect(() => {
        const fetchFinancialGoal = async () => {
            try {
                const goal = await clientService.getFinancialGoal();
                setFinancialGoal(goal);
            } catch (error) {
                console.error('Failed to fetch financial goal:', error);
            }
        };

        fetchFinancialGoal();
        
    }, []);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleSave = async () => {
        const newGoal = { name: goalName, description: goalDescription, targetValue: goalTargetValue, targetDate: goalTargetDate };
        await clientService.addFinancialGoal(newGoal);
        handleClose();

        try {
            const goal = await clientService.getFinancialGoal();
            setFinancialGoal(goal);
        } catch (error) {
            console.error('Failed to fetch financial goal:', error);
        }
    };

    if (!financialGoal) {
        return (
            <div className="framed-container">
                <div className="goal-header">
                    <div className='component-title'>Financial Goal</div>
                    <div className="add-goal" onClick={handleClickOpen}>
                        <AddIcon className="add-goal-icon" />
                        <span>Add goal</span>
                    </div>
                </div>
                <div className="goal-details">
                    <p>No financial goal set.</p>
                </div>
                <Dialog open={open} onClose={handleClose}>
                    <DialogTitle>Add Financial Goal</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            Please enter the details of your new financial goal.
                        </DialogContentText>
                        <TextField
                            autoFocus
                            margin="dense"
                            label="Goal Name"
                            type="text"
                            fullWidth
                            variant="standard"
                            value={goalName}
                            onChange={(e) => setGoalName(e.target.value)}
                        />
                        <TextField
                            margin="dense"
                            label="Description"
                            type="text"
                            fullWidth
                            variant="standard"
                            value={goalDescription}
                            onChange={(e) => setGoalDescription(e.target.value)}
                        />
                        <TextField
                            margin="dense"
                            label="Target Value"
                            type="number"
                            fullWidth
                            variant="standard"
                            value={goalTargetValue}
                            onChange={(e) => setGoalTargetValue(e.target.value)}
                        />
                        <TextField
                            margin="dense"
                            label="Target Date"
                            type="date"
                            fullWidth
                            variant="standard"
                            InputLabelProps={{ shrink: true }}
                            value={goalTargetDate}
                            onChange={(e) => setGoalTargetDate(e.target.value)}
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose}>Cancel</Button>
                        <Button onClick={handleSave}>Save</Button>
                    </DialogActions>
                </Dialog>
            </div>
        );
    }

    const target = financialGoal.targetValue;
    const current = financialGoal.currentBalance;
    const progress = (current / target) * 100;

    return (
        <div className="framed-container">
            <div className="goal-header">
                <div className='component-title'>Financial Goal</div>
                {!financialGoal.name && (
                    <div className="add-goal" onClick={handleClickOpen}>
                        <AddIcon className="add-goal-icon" />
                        <span>Add goal</span>
                    </div>
                )}
            </div>
            <div className="goal-details">
                <div>
                    <p style={{ fontWeight: "500", marginBottom: "0" }}>{financialGoal.name}</p>
                    <p style={{ fontWeight: "200", fontSize: "14px", marginTop: "0px" }}>{financialGoal.description}</p>
                    <span style={{ color: "gray", fontSize: "12px" }}>Target: $</span>
                    <span style={{ color: "gray", fontSize: "12px" }}>{financialGoal.targetValue}</span>
                    <div style={{ marginBottom: "20px" }}></div>
                    <span>Saving: $</span>
                    <span>{financialGoal.currentBalance}</span>
                </div>
                <LinearProgress style={{ marginTop: "10px" }} variant="determinate" value={progress} sx={{ bgcolor: '#f0f0f0', '& .MuiLinearProgress-bar': { bgcolor: '#ff5722' } }}/>
            </div>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Add Financial Goal</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Please enter the details of your new financial goal.
                    </DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        label="Goal Name"
                        type="text"
                        fullWidth
                        variant="standard"
                        value={goalName}
                        onChange={(e) => setGoalName(e.target.value)}
                    />
                    <TextField
                        margin="dense"
                        label="Description"
                        type="text"
                        fullWidth
                        variant="standard"
                        value={goalDescription}
                        onChange={(e) => setGoalDescription(e.target.value)}
                    />
                    <TextField
                        margin="dense"
                        label="Target Value"
                        type="number"
                        fullWidth
                        variant="standard"
                        value={goalTargetValue}
                        onChange={(e) => setGoalTargetValue(e.target.value)}
                    />
                    <TextField
                        margin="dense"
                        label="Target Date"
                        type="date"
                        fullWidth
                        variant="standard"
                        InputLabelProps={{ shrink: true }}
                        value={goalTargetDate}
                        onChange={(e) => setGoalTargetDate(e.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default FinancialGoal;
