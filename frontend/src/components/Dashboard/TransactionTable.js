import React from 'react';
import './TransactionTable.css';

const transactions = [
  { date: '2024-01-01', category: 'private', amount: 5000 },
  { date: '2024-01-02', category: 'food', amount: -150 },
  { date: '2024-01-03', category: 'food', amount: -100 },
  { date: '2024-01-04', category: 'shooping', amount: -5400 },
];

const TransactionTable = () => (
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
          <td>{transaction.amount}</td>
        </tr>
      ))}
    </tbody>
  </table>
);

export default TransactionTable;
