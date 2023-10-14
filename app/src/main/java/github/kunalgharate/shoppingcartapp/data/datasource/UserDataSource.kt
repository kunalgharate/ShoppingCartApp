package github.kunalgharate.shoppingcartapp.data.datasource

import github.kunalgharate.shoppingcartapp.data.model.Customer


// UserDataSource.kt
class UserDataSource {
    private val userList: List<Customer> = listOf(
        Customer("Regular"),
        Customer("Microsoft"),
        Customer("Amazon"),
        Customer("Facebook"),
        // Add more users as needed
    )

    fun getUsers(): List<Customer> {
        return userList
    }

    fun getUserByName(name: String): Customer? {
        return userList.find { it.name.equals(name, ignoreCase = true) }
    }
}
