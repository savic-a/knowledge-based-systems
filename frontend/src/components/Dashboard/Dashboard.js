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
      <div className="profile-info">
        <img src="/images/profile.png" alt="Profile" style={{ width: '40px', height: '40px', borderRadius: '50%', marginRight: '10px' }} />
        <div style={{ display: 'flex', flexDirection: 'column' }}>
          <div style={{ display: 'flex', alignItems: 'center' }}>
            <span style={{ margin: '0 5px 0 15px', fontWeight: 'normal' }}>Hello, </span> 
            <span style={{ fontWeight: '500' }}>Pera</span>
          </div>
          <div style={{ fontWeight: 'normal', fontSize: '12px', color:"gray", marginLeft: "15px" }}>Let's manage your wallet finance</div>
        </div>
      </div>
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