import React, { useEffect, useState } from 'react';
import clientService from '../../services/ClientService';

const CardBalance = ({ clientId }) => {
  const [creditCards, setCreditCards] = useState([]);

  useEffect(() => {
    const fetchCreditCards = async () => {
      try {
        const cards = await clientService.getCardBalance(clientId);
        console.log(cards)
        setCreditCards(cards);
      } catch (error) {
        console.error('Failed to fetch credit cards:', error);
      }
    };

    fetchCreditCards();
  }, [clientId]);

  return (
      <div className="framed-container">
        <div className='component-title'>Card Balance</div>
        <span>$</span>
        <span>{creditCards.balance}</span>
      </div>
    )
};

export default CardBalance;
