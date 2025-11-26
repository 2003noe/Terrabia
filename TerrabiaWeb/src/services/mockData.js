// src/services/mockData.js
export const categories = [
  { id: 1, name: "Fruits" },
  { id: 2, name: "Vegetables" },
  { id: 3, name: "Grains" },
  { id: 4, name: "Food" },
  { id: 5, name: "Spices" },
];

export const products = [
  { id: 1, categoryId: 2, ratings: 3.2, name: "Carrots", price: 1200, image: "/src/assets/img/products/carrots.png" },
  { id: 2, categoryId: 1, ratings: 1.3, name: "Cucumber", price: 600, image: "/src/assets/img/products/cucumber.png" },
  { id: 3, categoryId: 4, ratings: 4.1, name: "Irish potatoes", price: 3000, image: "/src/assets/img/products/irish-potatoes.png" },
  { id: 4, categoryId: 5, ratings: 2.5, name: "Onions", price: 3000, image: "/src/assets/img/products/onion.png" },
  { id: 5, categoryId: 1, ratings: 4.8, name: "Tomotoes", price: 3000, image: "/src/assets/img/products/tomatoes.png" },
  { id: 6, categoryId: 4, ratings: 3.7, name: "Sweet Potatoes", price: 500, image: "/src/assets/img/products/sweet-potatoes.png" },
];
