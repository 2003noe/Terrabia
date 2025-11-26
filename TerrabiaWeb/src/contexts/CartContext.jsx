// src/context/CartContext.js
import React, { createContext, useContext, useReducer, useEffect } from "react";

// Actions
const ADD_TO_CART = "ADD_TO_CART";
const REMOVE_FROM_CART = "REMOVE_FROM_CART";
const CLEAR_CART = "CLEAR_CART";

// Initial State
const initialState = {
  items: JSON.parse(localStorage.getItem("cart")) || [],
};

// Reducer
const cartReducer = (state, action) => {
    console.log("state: ", state, "action: ", action);
  switch (action.type) {
    case ADD_TO_CART:
      const existingItem = state.items.find((i) => i.id === action.payload.id);
      if (existingItem) {
        return {
          ...state,
          items: state.items.map((i) =>
            i.id === action.payload.id ? { ...i, quantity: i.quantity + 1 } : i
          ),
        };
      }
      return { ...state, items: [...state.items, { ...action.payload, quantity: 1 }] };

    case REMOVE_FROM_CART:
      return { ...state, items: state.items.filter((i) => i.id !== action.payload) };

    case CLEAR_CART:
      return { ...state, items: [] };

    default:
      return state;
  }
};

// Create Context
const CartContext = createContext();

// Provider
export const CartProvider = ({ children }) => {
  const [state, dispatch] = useReducer(cartReducer, initialState);

  // Persist cart to localStorage
  useEffect(() => {
    localStorage.setItem("cart", JSON.stringify(state.items));
  }, [state.items]);

  const addToCart = (product) => dispatch({ type: ADD_TO_CART, payload: product });
  const removeFromCart = (id) => dispatch({ type: REMOVE_FROM_CART, payload: id });
  const clearCart = () => dispatch({ type: CLEAR_CART });

  return (
    <CartContext.Provider value={{ cart: state.items, addToCart, removeFromCart, clearCart }}>
      {children}
    </CartContext.Provider>
  );
};

// Custom hook for easier usage
export const useCart = () => useContext(CartContext);
