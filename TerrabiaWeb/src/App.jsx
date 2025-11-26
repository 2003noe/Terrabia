import React from 'react'
import HomeScreen from './screens/buyer/HomeScreen/HomeScreen'
import { CartProvider } from './contexts/CartContext'


function App() {

  return (
   <CartProvider>
      <HomeScreen />
   </CartProvider>
  )
}

export default App
