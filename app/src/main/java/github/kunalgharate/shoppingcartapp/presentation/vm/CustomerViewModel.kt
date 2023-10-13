package github.kunalgharate.shoppingcartapp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.data.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _users: MutableLiveData<List<Customer>> = MutableLiveData()
    val users: LiveData<List<Customer>> get() = _users

    init {
        fetchUsers()
    }
    fun fetchUsers() {
        _users.value = userRepository.getUsers()
    }

    fun getUserByName(name: String): Customer? {
        return userRepository.getUserByName(name)
    }

}