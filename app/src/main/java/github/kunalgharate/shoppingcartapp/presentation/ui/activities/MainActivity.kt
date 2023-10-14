package github.kunalgharate.shoppingcartapp.presentation.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import github.kunalgharate.shoppingcartapp.R
import github.kunalgharate.shoppingcartapp.constants.AppConstants
import github.kunalgharate.shoppingcartapp.databinding.ActivityMainBinding
import github.kunalgharate.shoppingcartapp.presentation.adapter.ProductAdapter
import github.kunalgharate.shoppingcartapp.presentation.ui.fragments.CartFragment
import github.kunalgharate.shoppingcartapp.presentation.vm.ProductViewModel
import github.kunalgharate.shoppingcartapp.presentation.vm.ShoppingCartViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()
    private val shoppingCartVM : ShoppingCartViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var cartFragment: CartFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        viewModel.customer.set(intent.getParcelableExtra(AppConstants.customer))
        shoppingCartVM.customer.set(intent.getParcelableExtra(AppConstants.customer))
        binding.toolbar.title = "Customer : ${viewModel.customer.get()?.name}"
        viewModel.users.observe(this)
        { products ->
            if (!products.isNullOrEmpty()) {
                binding.productRecyclerView.adapter = ProductAdapter(products){ updatedCartList->
                    shoppingCartVM._cartItems.value = updatedCartList
                }
            }
        }

        shoppingCartVM.cartItems.observe(this)
        {
            binding.cartBtn.text = "Go to cart (${it.size})"
        }

        binding.cartBtn.setOnClickListener{
            showBottomSheetDialog()
        }


    }

    private fun showBottomSheetDialog() {
        if (cartFragment == null) {
            cartFragment =  CartFragment();
        }
        cartFragment!!.show(getSupportFragmentManager(), "bottom_sheet");
    }
}