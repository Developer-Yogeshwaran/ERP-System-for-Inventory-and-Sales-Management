import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import { Container, CssBaseline, AppBar, Toolbar, Typography } from '@mui/material';

import LoginPage from './pages/LoginPage';
import DashboardPage from './pages/DashboardPage';

function App() {
  // TODO: Replace with real auth state (JWT stored in localStorage / context)
  const isAuthenticated = !!localStorage.getItem('erpToken');

  return (
    <>
      <CssBaseline />
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div">
            ERP System
          </Typography>
        </Toolbar>
      </AppBar>
      <Container sx={{ mt: 4 }}>
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route
            path="/"
            element={isAuthenticated ? <DashboardPage /> : <Navigate replace to="/login" />}
          />
          <Route path="*" element={<Navigate replace to="/" />} />
        </Routes>
      </Container>
    </>
  );
}

export default App;
