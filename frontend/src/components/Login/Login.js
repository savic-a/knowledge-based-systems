import React, {useEffect, useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import theme from '../../theme';
import './Login.css'
import { ThemeProvider } from '@emotion/react';

import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import InputAdornment from '@mui/material/InputAdornment';
import TextField from '@mui/material/TextField';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Snackbar from '@mui/material/Snackbar';
import CloseIcon from '@mui/icons-material/Close';


const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [isButtonDisabled, setIsButtonDisabled] = useState(true);

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/;

    const navigate = useNavigate();

    const [open, setOpen] = React.useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState(''); 

    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
        event.target.value.trim() ===  '' ||  (!emailRegex.test(event.target.value.trim()) && event.target.value.trim() !==  'admin') || password.trim() === '' 
        ? checkButtonDisabled(true) : checkButtonDisabled(false)
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
        event.target.value.trim() ===  '' || !passwordRegex.test(event.target.value.trim()) || username.trim() === '' 
        ? checkButtonDisabled(true) : checkButtonDisabled(false)
    };

    const checkButtonDisabled = (value) => {
        value ? setIsButtonDisabled(true) : setIsButtonDisabled(false);
    };

    // snackbar
    const handleClick = () => {
        setOpen(true);
    };

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setOpen(false);
    };

    // login
    const handleLogin = async () => {
        // const result = await cognitoService.handleSignIn({"username": username, "password": password});
    
        // if (result) {
        //     // navigate('/main')
        //     await cognitoService.currentUser();
        // } else {
        //     setSnackbarMessage("Invalid email or password");
        //     handleClick()
        // }
    };

    const action = (
        <React.Fragment>
            <IconButton
            size="small"
            aria-label="close"
            color="inherit"
            onClick={handleClose}>
            <CloseIcon fontSize="small" />
            </IconButton>
        </React.Fragment>
    );


    return (
    <ThemeProvider theme={theme}>
    <div className='ground'>

    <div className='form'>
        <div id="card-header">
            <div className="left">
                <p className="welcome">Welcome to Money Mind</p>
            </div>
            <div className="title-login">Login</div>
        </div>

        <form>
            <div className='input-fields'>
                <div className='label'> Email:</div>
                <TextField
                    value={username}
                    onChange={handleUsernameChange}
                    id="email"
                    className='text-field'
                    sx={{ m: 1, width: '30ch' }}
                    placeholder="someone@example.com"
                    helperText="Required"
                    type='email'
                />
            </div>    

            <div className='input-fields'>
                <div className='label'>Password:</div>
                <TextField
                    id="password"
                    className='text-field'
                    type={showPassword ? 'text' : 'password'}
                    sx={{ m: 1, width: '30ch' }}
                    helperText="Required. Min 8 characters, special character, capital latter, low latter, number"
                    value={password}
                    onChange={handlePasswordChange}
                    required
                    InputProps={{
                    endAdornment: (
                        <InputAdornment position="end">
                            <IconButton
                                aria-label="toggle password visibility"
                                onClick={handleClickShowPassword}
                                onMouseDown={handleMouseDownPassword}>
                                    {showPassword ? <VisibilityOff /> : <Visibility />}
                            </IconButton>
                        </InputAdornment>
                    ),
                    }}
                />
            </div>

            <Button 
                id='signup'
                variant="contained" 
                color="primary" 
                disabled={isButtonDisabled}
                onClick={handleLogin}
                style={{marginTop: "50px", textTransform: 'none'}} 
                sx={{ m: 1, width: '100%' }}>
                    Login
            </Button>

            <Snackbar
                open={open}
                autoHideDuration={1000}
                onClose={handleClose}
                message={snackbarMessage}
                action={action}/>
        </form>
    </div>
    </div>
    </ThemeProvider>
    );
};

export default Login;