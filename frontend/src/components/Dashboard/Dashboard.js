import React from 'react';
import Navigation from '../Navigation/Navigation';
import './Dashboard.css';
import Chart from './Chart';
import TransactionTable from './TransactionTable';
import CardBalance from './CardBalance';
import FinancialGoal from './FinancialGoal';

const Dashboard = () => {
  return (
    <div>
      <Navigation />
      <div className="main-content">
        <div className="left-side">
          <div className="chart-area">
            <Chart />
          </div>
          <div className="transaction-table-area">
            <TransactionTable />
          </div>
        </div>
        <div className="delimiter"></div> 
        <div className="right-side">
          <div className="card-balance-area">
            <CardBalance />
          </div>
          <div className="financial-goal-area">
            <FinancialGoal />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
