package github.kunalgharate.shoppingcartapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.data.model.ShoppingCartItem
import github.kunalgharate.shoppingcartapp.data.repositories.ProductRepository
import github.kunalgharate.shoppingcartapp.domain.PricingRules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@HiltAndroidTest
class PricingRuleTestingUsingHilt {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var productRepository: ProductRepository


    @Before
    fun setup() {
        hiltRule.inject()
    }


        @Test
    fun testPricingRulesForMicrosoftCustomer() {
        val customer = Customer("Microsoft")
        val smallPizza = productRepository.getProductByName("Small Pizza")
        if (smallPizza != null) {
            val cartItem1 = ShoppingCartItem(smallPizza, 3) // 3 Small Pizzas for Microsoft

            val pricingResult = PricingRules.rules[customer]?.invoke(cartItem1)
            assert(pricingResult == 23.98) // Price for 3 Small Pizzas with 3 for 2 deal should be 20.0
        } else {
            assert(false) // If the product is not found, fail the test
        }
       }
}