import React from 'react'
import { useCart } from '../../../../contexts/CartContext'

const CartBadge = () =>  {
    const { cart } = useCart();
  return (
    <div className='relative'>
        <button className="bg-gray-200 p-2 rounded-full hover:bg-gray-300">
        🛒
      </button>
      {cart.length > 0 && (
        <span className="absolute -top-2 -right-2 bg-red-500 text-white rounded-full w-5 h-5 flex items-center justify-center text-xs">
          {cart.length}
        </span>
      )}
    </div>
  )
}

export default CartBadge