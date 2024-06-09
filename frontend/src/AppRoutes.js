import React from "react";
import Login from "./components/Login/Login";
import Dashboard from "./components/Dashboard/Dashboard";

const AppRoutes = [
    {
      path: '/',
      element: <Login />
    },  
    {
        path: '/dashboard',
        element: <Dashboard />
    },  
];
  
export default AppRoutes;
  