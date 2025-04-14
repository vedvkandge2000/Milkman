import { registerRootComponent } from 'expo';
import React from 'react';
import { Provider as PaperProvider } from 'react-native-paper';
import LandingPage from './screens/landingPage';

export default function App() {
  return (
      <PaperProvider>
        <LandingPage />
      </PaperProvider>
  );
}

registerRootComponent(App);