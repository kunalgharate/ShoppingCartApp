package github.kunalgharate.shoppingcartapp.presentation.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.search.SearchView.Behavior
import com.google.android.material.snackbar.Snackbar
import github.kunalgharate.shoppingcartapp.R
import github.kunalgharate.shoppingcartapp.constants.AppConstants
import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.data.model.Item
import github.kunalgharate.shoppingcartapp.data.model.ShoppingCartItem
import github.kunalgharate.shoppingcartapp.databinding.FragmentCartBinding
import github.kunalgharate.shoppingcartapp.domain.PricingRules
import github.kunalgharate.shoppingcartapp.presentation.adapter.CartAdapter
import github.kunalgharate.shoppingcartapp.presentation.vm.ShoppingCartViewModel

class CartFragment : BottomSheetDialogFragment() {

    private val shoppingCartVM: ShoppingCartViewModel by activityViewModels()
    private val behavior by lazy { (dialog as BottomSheetDialog).behavior }
    private lateinit var binding: FragmentCartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoppingCartVM.cartItems.observe(this)
        {
            binding.toolbar.title = "${shoppingCartVM.customer.get()?.name?:"default"} Cart ( ${it.size} )"
            binding.cartRecyclerView.adapter = CartAdapter(it)
            calculatePricingAndApplyOffer(it, shoppingCartVM.customer.get()?.name?:"default")
        }

        binding.placeOrderBtn.setOnClickListener{
            val snackbar = Snackbar.make(view, AppConstants.orderPlacedMessage, Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction(AppConstants.orderPlacedAction) {
                requireActivity().finish()
            }
            snackbar.show()
        }
    }

    private fun applyOffer(item: ShoppingCartItem, customerName: String): Double {
        val customer = Customer(customerName)
        val rule = PricingRules.rules[customer] ?: PricingRules.rules[Customer("default")]

        return rule?.invoke(item) ?: item.item.price
    }

    private fun calculatePricingAndApplyOffer(
        itemList: List<ShoppingCartItem>,
        customerName: String
    ) {
        val updatedItemList = itemList.map { item ->
            val newPrice = applyOffer(item, customerName)
            item.item.copy(price = newPrice)
        }
        val totalPrice = updatedItemList.sumOf { it.price }
        binding.totalPrice.text = " Total price is  : ${totalPrice}"

    }


}