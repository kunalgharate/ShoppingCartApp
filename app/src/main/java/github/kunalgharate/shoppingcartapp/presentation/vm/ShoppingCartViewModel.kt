package github.kunalgharate.shoppingcartapp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.kunalgharate.shoppingcartapp.domain.PricingRules
import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.data.model.Item
import github.kunalgharate.shoppingcartapp.data.model.ShoppingCartItem

class ShoppingCartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<ShoppingCartItem>>()
    val cartItems: LiveData<MutableList<ShoppingCartItem>> get() = _cartItems

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> get() = _totalPrice

    private val pricingRules = PricingRules()

    init {
        _cartItems.value = mutableListOf()
        _totalPrice.value = 0.0
    }

    fun addItemToCart(item: Item) {
        val existingItem = _cartItems.value?.find { it.item == item }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            _cartItems.value?.add(ShoppingCartItem(item, 1))
        }
        recalculateTotalPrice()
    }

    fun removeItemFromCart(item: Item) {
        val existingItem = _cartItems.value?.find { it.item == item }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                _cartItems.value?.remove(existingItem)
            }
            recalculateTotalPrice()
        }
    }

    private fun recalculateTotalPrice() {
        val total = _cartItems.value?.sumByDouble { pricingRules.rules[Customer("default")]?.invoke(it.item) ?: it.item.price * it.quantity } ?: 0.0
        _totalPrice.value = total
    }
}