import React from "react";
import Login from "./components/Login/Login";
import Dashboard from "./components/Dashboard/Dashboard";
import Reports from "./components/Reports/Reports";

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
];
  
export default AppRoutes;
  