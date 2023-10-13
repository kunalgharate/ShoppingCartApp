package github.kunalgharate.shoppingcartapp.data.repositories

import github.kunalgharate.shoppingcartapp.data.datasource.UserDataSource
import github.kunalgharate.shoppingcartapp.data.model.Customer


class UserRepository(private val userDataSource: UserDataSource) {
    fun getUsers(): List<Customer> {
        return userDataSource.getUsers()
    }

    fun getUserByName(name: String): Customer? {
        return userDataSource.getUserByName(name)
    }
}