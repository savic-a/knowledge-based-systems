import React, { useState } from 'react';
import './Navigation.css';
import DashboardIcon from '@mui/icons-material/Dashboard';
import LogoutIcon from '@mui/icons-material/Logout';

const Navigation = () => {
    const [selected, setSelected] = useState('Dashboard');

    const handleSelect = (option) => {
      setSelected(option);
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