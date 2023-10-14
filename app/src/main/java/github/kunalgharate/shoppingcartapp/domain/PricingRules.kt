package github.kunalgharate.shoppingcartapp.domain

import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.data.model.Item
import github.kunalgharate.shoppingcartapp.data.model.ShoppingCartItem

object PricingRules {

    val rules = mutableMapOf<Customer, (ShoppingCartItem) -> Double>()

    init {
        // Default pricing rules
        rules[Customer("Regular")] = { item -> item.item.price }
        // Microsoft Gets a 3 for 2 deal for Small Pizzas
        rules[Customer("Microsoft")] = { item ->
            when {
                item.item.name == "Small Pizza" -> {
                    val setsOfThree = item.quantity / 3
                    val remainingItems = item.quantity % 3
                    (setsOfThree * 2 + remainingItems) * item.item.price
                }
                else -> item.item.price * item.quantity
            }
        }
        // Amazon Gets a discount on Large Pizza where the price drops to $19.99 per pizza
        rules[Customer("Amazon")] = { item ->
            if (item.item.name  == "Large Pizza") {
                19.99 * item.quantity
            } else {
                    item.item.price  * item.quantity
            }
        }
        // Facebook Gets a 5 for 4 deal on Medium Pizza
        rules[Customer("Facebook")] = { item ->
            when {
                item.item.name  == "Medium Pizza" -> {
                    val setsOfFive = item.quantity / 5
                    val remainingItems = item.quantity % 5
                    (setsOfFive * 4 + remainingItems) * item.item.price
                }
                else -> item.item.price * item.quantity
            }
        }
    }
}
