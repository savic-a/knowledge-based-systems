import React, { useEffect, useState } from 'react';
import clientService from '../../services/ClientService';

const TransactionTable = () => {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const fetchedTransactions = await clientService.getTransactions();
        const transactionsWithFormattedDate = fetchedTransactions.map(transaction => ({
          ...transaction,
          date: new Date(transaction.date).toLocaleDateString() 
        }));
        setTransactions(transactionsWithFormattedDate);
      } catch (error) {
        console.error('Failed to fetch transactions:', error);
      }
    };

    fetchTransactions();
  }, []);

  return (
    <div className="framed-container">
      <div className='component-title'>Transaction history</div>
      <table>
        <thead>
          <tr>
            <th>Date</th>
            <th>Category</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction, index) => (
            <tr key={index}>
              <td>{transaction.date}</td>
              <td>{transaction.category}</td>
              <td>{transaction.value}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TransactionTable;
