import React, { useEffect, useState } from 'react';
import clientService from '../../services/ClientService';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';

const CardBalance = () => {
  const [creditCards, setCreditCards] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  // State za dijalog PAY
  const [openPayDialog, setOpenPayDialog] = useState(false);
  const [transactionValue, setTransactionValue] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('');

  // State za dijalog ADD MONEY
  const [openAddMoneyDialog, setOpenAddMoneyDialog] = useState(false);
  const [moneyToAdd, setMoneyToAdd] = useState('');

  useEffect(() => {
    const fetchCreditCards = async () => {
      try {
        const cards = await clientService.getCardBalance();
        setCreditCards(cards);
      } catch (error) {
        console.error('Failed to fetch credit cards:', error);
      }
    };

    fetchCreditCards();
  }, []);

  const handlePayment = async () => {
    setOpenPayDialog(true);
  };

  const handleClosePayDialog = () => {
    setOpenPayDialog(false);
  };

  const handleConfirmPayment = async () => {
    try {
      setIsLoading(true);
      // Logika za potvrdu plaćanja
      // Ovdje bi se obavila potvrda plaćanja sa unetom vrednošću i izabranom kategorijom
      console.log('Plaćanje je uspešno obavljeno!');
      handleClosePayDialog();
    } catch (error) {
      console.error('Failed to process payment:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleAddMoney = async () => {
    setOpenAddMoneyDialog(true);
  };

  const handleCloseAddMoneyDialog = () => {
    setOpenAddMoneyDialog(false);
  };

  const handleConfirmAddMoney = async () => {
    try {
      setIsLoading(true);
      // Logika za dodavanje novca na račun
      // Ovdje bi se obavila logika za dodavanje novca na račun sa unetom vrednošću
      console.log('Novac je uspešno dodat na račun!');
      handleCloseAddMoneyDialog();
    } catch (error) {
      console.error('Failed to add funds:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const categories = [
    { value: 'FOOD', label: 'Food' },
    { value: 'ENTERTAINMENT', label: 'Entertainment' },
    { value: 'SHOPPING', label: 'Shopping' },
    { value: 'OUTINGS', label: 'Outings' },
    { value: 'HEALTH_AND_CARE', label: 'Health and Care' },
    { value: 'PRIVATE', label: 'Private' },
  ];

  return (
    <div className="framed-container">
      <div className='component-title'>Card Balance</div>
      <span>$</span>
      <span>{creditCards.balance}</span>
      <div>
        <Button onClick={handlePayment} disabled={isLoading} variant="contained" style={{backgroundColor: 'black', color: 'white', marginTop: '15px'}}>Pay</Button>
        <Button onClick={handleAddMoney} disabled={isLoading} variant="contained" style={{ backgroundColor: 'white', color: 'black', float: 'right', marginTop: '15px' }}>Add money</Button>

        {/* PAY dijalog */}
        <Dialog open={openPayDialog} onClose={handleClosePayDialog}>
          <DialogTitle>Confirm Payment</DialogTitle>
          <DialogContent>
            <DialogContentText>
              Enter transaction value and select category:
            </DialogContentText>
            <TextField
              autoFocus
              margin="dense"
              id="transactionValue"
              label="Transaction Value"
              type="number"
              fullWidth
              value={transactionValue}
              onChange={(e) => setTransactionValue(e.target.value)}
            />
            <TextField
              id="category"
              select
              fullWidth
              label="Category"
              value={selectedCategory}
              onChange={(e) => setSelectedCategory(e.target.value)}
              margin="dense"
            >
              {categories.map((option) => (
                <MenuItem key={option.value} value={option.value}>
                  {option.label}
                </MenuItem>
              ))}
            </TextField>
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClosePayDialog}>Cancel</Button>
            <Button onClick={handleConfirmPayment} variant="contained" style={{ backgroundColor: 'black', color: 'white' }}>Confirm</Button>
          </DialogActions>
        </Dialog>

        {/* ADD MONEY dijalog */}
        <Dialog open={openAddMoneyDialog} onClose={handleCloseAddMoneyDialog}>
          <DialogTitle>Add Money</DialogTitle>
          <DialogContent>
            <DialogContentText>
              Enter the amount you want to add:
            </DialogContentText>
            <TextField
              autoFocus
              margin="dense"
              id="moneyToAdd"
              label="Amount"
              type="number"
              fullWidth
              value={moneyToAdd}
              onChange={(e) => setMoneyToAdd(e.target.value)}
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={handleCloseAddMoneyDialog}>Cancel</Button>
            <Button onClick={handleConfirmAddMoney} variant="contained" style={{ backgroundColor: 'black', color: 'white' }}>Confirm</Button>
          </DialogActions>
        </Dialog>

      </div>
    </div>
  );
};

export default CardBalance;
