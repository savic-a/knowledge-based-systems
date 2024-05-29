import React from "react";
import Login from "./components/Login/Login";
import Navigation from "./components/Navigation/Navigation";

const AppRoutes = [
    {
      path: '/',
      element: <Login />
    },  
    {
        path: '/nav',
        element: <Navigation />
    },  
];
  
export default AppRoutes;
  