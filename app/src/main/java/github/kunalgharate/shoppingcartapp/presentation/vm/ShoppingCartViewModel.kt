package github.kunalgharate.shoppingcartapp.presentation.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.kunalgharate.shoppingcartapp.domain.PricingRules
import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.data.model.Item
import github.kunalgharate.shoppingcartapp.data.model.ShoppingCartItem

class ShoppingCartViewModel : ViewModel() {
     val _cartItems = MutableLiveData<MutableList<ShoppingCartItem>>()
    val cartItems: LiveData<MutableList<ShoppingCartItem>> get() = _cartItems
    val customer = ObservableField<Customer>()
}