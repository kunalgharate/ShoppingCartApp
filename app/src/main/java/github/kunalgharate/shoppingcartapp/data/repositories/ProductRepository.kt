package github.kunalgharate.shoppingcartapp.data.repositories

import github.kunalgharate.shoppingcartapp.data.datasource.ProductDataSource
import github.kunalgharate.shoppingcartapp.data.model.Item

class ProductRepository(private val productDataSource: ProductDataSource) {

    fun getProducts(): List<Item> {
        return productDataSource.getProducts()
    }
}