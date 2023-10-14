package github.kunalgharate.shoppingcartapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.kunalgharate.shoppingcartapp.R
import github.kunalgharate.shoppingcartapp.data.model.Item
import github.kunalgharate.shoppingcartapp.data.model.ShoppingCartItem

class CartAdapter(private val cartItems: List<ShoppingCartItem>) : RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_layout, parent, false)
        return CartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val item = cartItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = cartItems.size


    inner class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
        private val itemQuantityTextView: TextView = itemView.findViewById(R.id.itemQuantityTextView)

        fun bind(cartItem: ShoppingCartItem) {
            itemNameTextView.text = "${cartItem.item.name}  \$${cartItem.item.price} "
            itemQuantityTextView.text = " x ${cartItem.quantity}"
        }
    }
}
