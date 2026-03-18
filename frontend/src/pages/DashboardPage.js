import React from 'react';
import { Button, Typography, Box, Paper } from '@mui/material';

export default function DashboardPage() {
  const handleLogout = () => {
    localStorage.removeItem('erpToken');
    window.location.reload();
  };

  return (
    <Paper sx={{ p: 4 }}>
      <Typography variant="h4" gutterBottom>
        Dashboard
      </Typography>
      <Typography gutterBottom>
        Welcome to the ERP system demo. Use the navigation to explore modules.
      </Typography>

      <Box sx={{ mt: 3 }}>
        <Button variant="contained" color="secondary" onClick={handleLogout}>
          Sign out
        </Button>
      </Box>
    </Paper>
  );
}
