import React, { useEffect, useState } from 'react';
import Navigation from '../Navigation/Navigation';
import './Reports.css';
import clientService from '../../services/ClientService';
import { Button } from '@mui/material';
  

const Reports = () => {
  const [reports, setReports] = useState([]);
  const [newReportReason, setNewReportReason] = useState('');

  useEffect(() => {
    const fetchReports = async () => {
      try {
        const fetchedreports = await clientService.getReports();
        const reportsWithFormattedDate = fetchedreports.map(report => ({
          ...report,
          date: new Date(report.generationDate).toLocaleDateString() 
        }));
        setReports(reportsWithFormattedDate);
      } catch (error) {
        console.error('Failed to fetch reports:', error);
      }
    };

    fetchReports();
  }, []);

  const handleAddReport = async () => {
    const newReport = {reason: newReportReason};
    await clientService.addReport(newReport)
    
    try {
      const reports = await clientService.getReports();
      const reportsWithFormattedDate = reports.map(report => ({
        ...report,
        date: new Date(report.generationDate).toLocaleDateString() 
      }));
      setReports(reportsWithFormattedDate);
      setNewReportReason("");
    } catch (error) {
      console.error('Failed to fetch financial goal:', error);
    }
  };

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

      <div className="add-report-container">
          <input
            type="text"
            value={newReportReason}
            onChange={(e) => setNewReportReason(e.target.value)}
            placeholder="Enter report reason"
          />
          <Button
            variant="contained"
            color="primary"
            onClick={handleAddReport}
            disabled={!newReportReason.trim()}
          >
            Add Report
          </Button>
        </div>
    </div>
    </div>
  );
};

export default Reports;
