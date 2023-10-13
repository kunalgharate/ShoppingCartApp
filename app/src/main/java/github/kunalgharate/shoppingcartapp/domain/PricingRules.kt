package github.kunalgharate.shoppingcartapp.domain

import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.data.model.Item

class PricingRules {
    val rules = mutableMapOf<Customer, (Item) -> Double>()

    init {
        // Default pricing rules
        rules[Customer("default")] = { item -> item.price }
        rules[Customer("Microsoft")] = { item -> if (item.name == "Small Pizza") item.price * 2 / 3 else item.price }
        rules[Customer("Amazon")] = { item -> if (item.name == "Large Pizza") 19.99 else item.price }
        rules[Customer("Facebook")] = { item -> if (item.name == "Medium Pizza") item.price * 4 / 5 else item.price }
    }
}