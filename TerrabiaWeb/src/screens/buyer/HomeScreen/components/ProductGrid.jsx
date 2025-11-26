import React from "react";
import { useCart } from "../../../../contexts/CartContext";

const ProductGrid = ({ products }) => {

    const { addToCart } = useCart();

  return (
    <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mt-4">
      {products.map((product) => (
        <div key={product.id} className="border rounded-lg p-4 hover:shadow-lg transition">
          <img src={product.image} alt={product.name} className="w-full h-32 object-cover rounded-md" />
          <h3 className="mt-2 font-semibold">{product.name}</h3>
          <p className="text-green-600 font-bold">XAF{product.price.toFixed(2)}</p>
          <button 
            onClick={() => addToCart(product)}
            className="mt-2 w-full bg-green-500 text-white py-1 rounded hover:bg-green-600">
            Add to Cart
          </button>
        </div>
      ))}
    </div>
  );
};

export default ProductGrid;