package github.kunalgharate.shoppingcartapp

import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.data.model.Item
import github.kunalgharate.shoppingcartapp.data.model.ShoppingCartItem
import github.kunalgharate.shoppingcartapp.domain.PricingRules
import org.junit.Test


class PricingRulesTest {

    //    @Inject
//    lateinit var productRepository: ProductRepository
//
//    @Inject
//    lateinit var userRepository: UserRepository
//
//    @Before
//    fun setup() {
//        hiltRule.inject()
//    }
//
//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)



    //    @Test
//    fun `test pricing rules for Microsoft customer`() {
//        val customer = Customer("Microsoft")
//       // val smallPizza = productRepository.getProductByName("Small Pizza")
//        if (smallPizza != null) {
//            val cartItem1 = ShoppingCartItem(smallPizza, 3) // 3 Small Pizzas for Microsoft
//
//            val pricingResult = PricingRules.rules[customer]?.invoke(cartItem1)
//            assert(pricingResult == 23.98) // Price for 3 Small Pizzas with 3 for 2 deal should be 20.0
//        } else {
//            assert(false) // If the product is not found, fail the test
//        }
    //   }


    @Test
    fun `testPricingRulesForDefaultCustomer`() {
        val defaultCustomer = Customer("Regular")

        val smallPizza = Item("Small Pizza", "Delicious small pizza", 11.99)
        val mediumPizza = Item("Medium Pizza", "Delicious medium pizza", 15.99)
        val largePizza = Item("Large Pizza", "Delicious large pizza", 21.99)

        val cartItem1 = ShoppingCartItem(smallPizza, 1)
        val cartItem2 = ShoppingCartItem(mediumPizza, 1)
        val cartItem3 = ShoppingCartItem(largePizza, 1)

        val pricingResult1 = PricingRules.rules[defaultCustomer]?.invoke(cartItem1)
        val pricingResult2 = PricingRules.rules[defaultCustomer]?.invoke(cartItem2)
        val pricingResult3 = PricingRules.rules[defaultCustomer]?.invoke(cartItem3)

        val totalPrice = (pricingResult1 ?: 0.0) + (pricingResult2 ?: 0.0) + (pricingResult3 ?: 0.0)

        assert(totalPrice == 49.97)
    }

    @Test
    fun `testPricingRulesForMicrosoftCustomer`() {
        val microsoftCustomer = Customer("Microsoft")
        val smallPizza = Item("Small Pizza", "Delicious small pizza", 11.99)
        val largePizza = Item("Large Pizza", "Delicious large pizza", 21.99)

        val cartItem1 = ShoppingCartItem(smallPizza, 3)
        val cartItem2 = ShoppingCartItem(largePizza, 1)

        val pricingResult1 = PricingRules.rules[microsoftCustomer]?.invoke(cartItem1)
        val pricingResult2 = PricingRules.rules[microsoftCustomer]?.invoke(cartItem2)

        val totalPrice = (pricingResult1 ?: 0.0) + (pricingResult2 ?: 0.0)

        assert(totalPrice == 45.97)
    }

    @Test
    fun `testPricingRulesForAmazonCustomer`() {
        val amazonCustomer = Customer("Amazon")
        val mediumPizza = Item("Medium Pizza", "Delicious medium pizza", 15.99)
        val largePizza = Item("Large Pizza", "Delicious large pizza", 21.99)

        val cartItem1 = ShoppingCartItem(mediumPizza, 3)
        val cartItem2 = ShoppingCartItem(largePizza, 1)

        val pricingResult1 = PricingRules.rules[amazonCustomer]?.invoke(cartItem1)
        val pricingResult2 = PricingRules.rules[amazonCustomer]?.invoke(cartItem2)

        val totalPrice = (pricingResult1 ?: 0.0) + (pricingResult2 ?: 0.0)

        assert(totalPrice == 67.96)
    }

    @Test
    fun `testPricingRulesForFacebookCustomer`() {
        val facebookCustomer = Customer("Facebook")
        val mediumPizza = Item("Medium Pizza", "Delicious medium pizza", 15.99)
        val cartItem = ShoppingCartItem(mediumPizza, 5)

        val pricingResult = PricingRules.rules[facebookCustomer]?.invoke(cartItem)
        assert(pricingResult == 63.96) // Price for 5 Medium Pizzas with 5 for 4 deal should be 60.0
    }
}
