import React from "react";
import Login from "./components/Login/Login";
import Dashboard from "./components/Dashboard/Dashboard";
import Reports from "./components/Reports/Reports";
import Household from "./components/Household.js/Household";

const AppRoutes = [
    {
      path: '/',
      element: <Login />
    },  
    {
        path: '/dashboard',
        element: <Dashboard />
    }, 
    {
      path: '/reports',
      element: <Reports />
    },  
    {
      path: '/household',
      element: <Household />
    },  
];
  
export default AppRoutes;
  