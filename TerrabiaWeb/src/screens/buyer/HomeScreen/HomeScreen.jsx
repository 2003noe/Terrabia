import React, { useState } from "react";
import CategoryList from "./components/CategoryList";
import ProductGrid from "./components/ProductGrid";
import FilterModal from "./components/FilterModal";
import { categories, products } from "../../../services/mockData";
import CartBadge from "./components/CartBadge";

const HomeScreen = () => {
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [isFilterOpen, setIsFilterOpen] = useState(false);


  const filteredProducts = selectedCategory ? products.filter((p) => p.categoryId === selectedCategory)  : products;

  return (
    <div className="p-4">

      <header className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold"> Terrabia </h1>
        <div className="flex items-center space-x-4">
          <button
            className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
            onClick={() => setIsFilterOpen(true)}
          >
            Filters
          </button>
          <CartBadge />
        </div>
      </header>

      <CategoryList categories={categories} selectedCategory={selectedCategory} onSelectCategory={setSelectedCategory} />

      <ProductGrid products={filteredProducts} />

      <FilterModal isOpen={isFilterOpen} onClose={() => setIsFilterOpen(false)} />
    </div>
  );
};

export default HomeScreen;