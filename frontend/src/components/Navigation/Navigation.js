import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './Navigation.css';
import DashboardIcon from '@mui/icons-material/Dashboard';
import LogoutIcon from '@mui/icons-material/Logout';
import ReportIcon from '@mui/icons-material/Report';
import authService from '../../services/AuthService';
import { useNavigate } from 'react-router-dom';

const Navigation = ({option}) => {
    const [selected, setSelected] = useState(option);
    const navigate = useNavigate();

    const handleSelect = (option) => {
        setSelected(option);
        if(option === 'Dashboard') {
            navigate('/dashboard')
        }
        if (option === 'Reports') {
            navigate('/reports')
        }
        if (option === 'Logout') {
            handleLogout();
        }
    };

    const handleLogout = () => {
        authService.logOut();
        navigate('/'); 
    };

    return (
        <div>
\            <div className="drawer">
                <div className='app-title'>Money Mind</div>
                <ul>
                    <li
                        className={selected === 'Dashboard' ? 'selected' : ''}
                        onClick={() => handleSelect('Dashboard')}>
                        <DashboardIcon className="icon" />
                            Dashboard
                    
                    </li>
                    <li
                        className={selected === 'Reports' ? 'selected' : ''}
                        onClick={() => handleSelect('Reports')}>
                        <ReportIcon className="icon" />
                            Reports
                        
                    </li>
                    <li
                        className={selected === 'Logout' ? 'selected' : ''}
                        onClick={() => handleSelect('Logout')}
                        style={{ position: 'absolute', bottom: '0', left: '0', width: '200px' }}>
                            <LogoutIcon className="icon" />
                        Logout
                    </li>
                </ul>
            </div>
        </div>
    );
};

export default Navigation;