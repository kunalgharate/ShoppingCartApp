package github.kunalgharate.shoppingcartapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import github.kunalgharate.shoppingcartapp.R
import github.kunalgharate.shoppingcartapp.data.model.Item
import github.kunalgharate.shoppingcartapp.presentation.adapter.CartAdapter
import github.kunalgharate.shoppingcartapp.presentation.vm.ShoppingCartViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ShoppingCartViewModel
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ShoppingCartViewModel::class.java)

        cartRecyclerView = findViewById(R.id.cartRecyclerView)
        cartAdapter = CartAdapter()

        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        cartRecyclerView.adapter = cartAdapter

        // Initialize your items and customers here

        // Example: Add a Small Pizza to the cart
        val smallPizza = Item("Small Pizza", "10'' pizza for one person", 11.99)
        viewModel.addItemToCart(smallPizza)

        // Update the cartAdapter when cart items change
        viewModel.cartItems.observe(this, Observer { cartItems ->
            cartAdapter.submitList(cartItems)
        })

        // Update the total price when it changes
        viewModel.totalPrice.observe(this, Observer {
            // Update your UI to display the total price
        })
    }
}