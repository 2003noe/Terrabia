import React from "react";

const CategoryList = ({ categories, selectedCategory, onSelectCategory }) => {
  return (
    <div className="flex space-x-4 overflow-x-auto py-2">
      {categories.map((cat) => (
        <button
          key={cat.id}
          className={`px-4 py-2 rounded-full border ${
            selectedCategory === cat.id ? "bg-green-500 text-white" : "bg-white text-gray-700 border-gray-300"
          }`}
          onClick={() => onSelectCategory(cat.id)}
        >
          {cat.name}
        </button>
      ))}
    </div>
  );
};

export default CategoryList;