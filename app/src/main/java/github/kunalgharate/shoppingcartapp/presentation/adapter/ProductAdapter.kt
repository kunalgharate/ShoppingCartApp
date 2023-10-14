package github.kunalgharate.shoppingcartapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.kunalgharate.shoppingcartapp.R
import github.kunalgharate.shoppingcartapp.data.model.Item
import github.kunalgharate.shoppingcartapp.data.model.ShoppingCartItem

class ProductAdapter(private val itemList: List<Item>,private val cartListCallback: (MutableList<ShoppingCartItem>) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val cartList: MutableList<ShoppingCartItem> = mutableListOf()
    private val itemQuantityMap: MutableMap<String, Int> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_layout, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.productName.text = currentItem.name
        holder.productDescription.text = currentItem.description
        holder.productPrice.text = "$ ${currentItem.price}"
        holder.qtyTextView.text = itemQuantityMap[currentItem.name]?.toString() ?: "0"

        holder.addButton.setOnClickListener {
            val currentQuantity = itemQuantityMap[currentItem.name] ?: 0
            itemQuantityMap[currentItem.name] = currentQuantity + 1
            holder.qtyTextView.text = itemQuantityMap[currentItem.name].toString()
            val existingItem = cartList.find { it.item == currentItem }
            if (existingItem != null) {
                existingItem.quantity = currentQuantity + 1
            } else {
                cartList.add(ShoppingCartItem(currentItem, 1))
            }

            cartListCallback.invoke(cartList)
        }

        holder.removeButton.setOnClickListener {
            val currentQuantity = itemQuantityMap[currentItem.name] ?: 0
            if (currentQuantity > 0) {
                itemQuantityMap[currentItem.name] = currentQuantity - 1
                holder.qtyTextView.text = itemQuantityMap[currentItem.name].toString()
                val existingItem = cartList.find { it.item == currentItem }
                if (existingItem != null) {
                    existingItem.quantity = currentQuantity - 1
                    if (existingItem.quantity == 0) {
                        cartList.remove(existingItem)
                    }
                }
            }
            cartListCallback.invoke(cartList)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val qtyTextView: TextView = itemView.findViewById(R.id.txt_qty)
        val addButton: Button = itemView.findViewById(R.id.addButton)
        val removeButton: Button = itemView.findViewById(R.id.removeButton)
    }
}
