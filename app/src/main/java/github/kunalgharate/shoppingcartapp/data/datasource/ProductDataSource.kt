package github.kunalgharate.shoppingcartapp.data.datasource

import github.kunalgharate.shoppingcartapp.data.model.Item

class ProductDataSource {
    private val productList: List<Item> = listOf(
        Item("Small Pizza", "10'' pizza for one person", 11.99),
        Item("Medium Pizza", "12'' Pizza for two persons", 15.99),
        Item("Large Pizza", "15'' Pizza for four persons", 21.99)
    )

    fun getProducts(): List<Item> {
        return productList
    }
}