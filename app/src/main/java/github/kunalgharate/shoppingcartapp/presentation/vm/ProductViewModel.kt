package github.kunalgharate.shoppingcartapp.presentation.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.data.model.Item
import github.kunalgharate.shoppingcartapp.data.repositories.ProductRepository
import github.kunalgharate.shoppingcartapp.data.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val userRepository: ProductRepository) : ViewModel() {

    private val _users: MutableLiveData<List<Item>> = MutableLiveData()
    val users: LiveData<List<Item>> get() = _users
    val customer = ObservableField<Customer>()

    init {
        fetchUsers()
    }
    fun fetchUsers() {
        _users.value = userRepository.getProducts()
    }


}