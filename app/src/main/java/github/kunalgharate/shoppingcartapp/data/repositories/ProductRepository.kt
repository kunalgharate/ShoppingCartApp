package github.kunalgharate.shoppingcartapp.data.repositories

import github.kunalgharate.shoppingcartapp.data.datasource.ProductDataSource
import github.kunalgharate.shoppingcartapp.data.model.Item

open class ProductRepository(private val productDataSource: ProductDataSource) {

    fun getProducts(): List<Item> {
        return productDataSource.getProducts()
    }

    fun getProductByName(name: String): Item? {
        // Implement logic to get the product by name from the data source
        return productDataSource.getProductByName(name)
    }

}