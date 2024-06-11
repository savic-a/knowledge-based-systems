import React, { useState, useEffect } from 'react';
import { Button, TextField, Chip, Box } from '@mui/material';
import Navigation from '../Navigation/Navigation';
import './Household.css';
import clientService from '../../services/ClientService';

const Household = () => {
  const [emails, setEmails] = useState([]);
  const [emailInput, setEmailInput] = useState('');

  useEffect(() => {
    const fetchEmails = async () => {
      const existingEmails = await clientService.getHousehold();
      console.log(existingEmails);
      const emailList = existingEmails.map(item => item.email);
      setEmails(emailList);
    };

    fetchEmails();
  }, []);

  const handleEmailInputChange = (event) => {
    setEmailInput(event.target.value);
  };

  const handleAddEmail = () => {
    if (emailInput && !emails.includes(emailInput)) {
      setEmails([...emails, emailInput]);
      setEmailInput('');
    }
  };

  const handleDeleteEmail = (emailToDelete) => () => {
    setEmails(emails.filter((email) => email !== emailToDelete));
  };

  return (
    <div>
      <Navigation option={"Household"}/>
    <div className="household-container">
      <div className="component-title">Household</div>
      <Box display="flex" flexDirection="column" alignItems="center" width="100%">
      <Box display="flex" flexWrap="wrap" mb={2}>
            {emails.map((email, index) => (
              <Chip
                key={index}
                label={email}
                onDelete={handleDeleteEmail(email)}
                color="primary"
                sx={{ m: 0.5 }}
              />
            ))}
          </Box>
        <TextField
          label="Enter email"
          type="email"
          value={emailInput}
          onChange={handleEmailInputChange}
          placeholder="Enter email address"
          fullWidth
          margin="normal"
        />
        <Button
          variant="contained"
          color="primary"
          onClick={handleAddEmail}
          disabled={!emailInput}
          sx={{ mt: 2 }}
        >
          Add Email
        </Button>
      </Box>
    </div>
    </div>
  );
};

export default Household;
