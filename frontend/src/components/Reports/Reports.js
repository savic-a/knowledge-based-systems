import React, { useEffect, useState } from 'react';
import Navigation from '../Navigation/Navigation';
import './Reports.css';
import clientService from '../../services/ClientService';
  

const Reports = () => {
  const [reports, setReports] = useState([]);

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
