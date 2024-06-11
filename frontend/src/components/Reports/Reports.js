import React from 'react';
import Navigation from '../Navigation/Navigation';
import './Reports.css';

const reports = [
    { date: '2024-06-01', reason: 'System Update' },
    { date: '2024-06-02', reason: 'Scheduled Maintenance' },
];
  

const Reports = () => {
  return (
    <div>
        <Navigation option={"Reports"}/>
    <div className="report-container">
      <div className='component-title'>Report history</div>
      <table>
        <thead>
          <tr>
            <th>Date</th>
            <th>Reason</th>
          </tr>
        </thead>
        <tbody>
          {reports.map((report, index) => (
            <tr key={index}>
              <td>{report.date}</td>
              <td>{report.reason}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    </div>
  );
};

export default Reports;
