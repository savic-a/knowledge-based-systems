import React, { useEffect, useState } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import clientService from '../../services/ClientService';

const Chart = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const transactions = await clientService.getTransactions();
        const transformedData = transactions.reduce((acc, transaction) => {
          const date = new Date(transaction.date).toLocaleDateString('en-US', { month: 'short' });
          const existingEntry = acc.find(entry => entry.name === date);

          if (existingEntry) {
            if (transaction.type === 'INCOME') {
              existingEntry.income += transaction.value;
            } else if (transaction.type === 'OUTCOME') {
              existingEntry.expense += transaction.value;
            }
          } else {
            acc.push({
              name: date,
              income: transaction.type === 'INCOME' ? transaction.value : 0,
              expense: transaction.type === 'OUTCOME' ? transaction.value : 0
            });
          }

          return acc;
        }, []);

        setData(transformedData);
      } catch (error) {
        console.error('Failed to fetch transactions:', error);
      }
    };

    fetchTransactions();
    
  }, []);

  return (
    <div className="chart-container">
      <div className='component-title'>Analysis</div>
      <ResponsiveContainer width="100%" height={300}>
        <LineChart data={data} margin={{ top: 5, right: 30, left: 20, bottom: 5 }}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="income" stroke="#8884d8" activeDot={{ r: 8 }} />
          <Line type="monotone" dataKey="expense" stroke="#82ca9d" />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default Chart;
