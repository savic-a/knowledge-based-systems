import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      main: '#0A0A0A',
    },
    secondary: {
      main: '#FFFFFF',
    },
  },
  customStyles: {
    myCustomButton: {
      textTransform: 'none',
      fontFamily: 'Poppins',
      width: '37%',
    },
    // Add more custom styles as needed
  },
});

export default theme;
