import React from "react";

const FilterModal = ({ isOpen, onClose }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
      <div className="bg-white p-6 rounded-lg w-80">
        <h2 className="text-xl font-semibold mb-4">Filters</h2>
        <div className="space-y-3">
          <label className="block">
            <span className="text-gray-700">Price Range</span>
            <input type="range" className="w-full mt-1" min="0" max="100" />
          </label>
          <label className="block">
            <span className="text-gray-700">Rating</span>
            <select className="w-full mt-1 border rounded p-1">
              <option>Any</option>
              <option>1+ Stars</option>
              <option>2+ Stars</option>
              <option>3+ Stars</option>
              <option>4+ Stars</option>
              <option>5 Stars</option>
            </select>
          </label>
        </div>
        <button
          className="mt-4 w-full bg-green-500 text-white py-2 rounded hover:bg-green-600"
          onClick={onClose}
        >
          Apply Filters
        </button>
      </div>
    </div>
  );
};

export default FilterModal;