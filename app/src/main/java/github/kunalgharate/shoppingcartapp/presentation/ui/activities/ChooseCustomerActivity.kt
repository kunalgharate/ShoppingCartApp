package github.kunalgharate.shoppingcartapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import github.kunalgharate.shoppingcartapp.R
import github.kunalgharate.shoppingcartapp.databinding.ActivityChooseCustomerBinding
import github.kunalgharate.shoppingcartapp.presentation.adapter.UserListAdapter
import github.kunalgharate.shoppingcartapp.presentation.vm.CustomerViewModel

@AndroidEntryPoint
class ChooseCustomerActivity : AppCompatActivity() {
    companion object{
        const val TAG ="ChooseCustomerActivity"
    }
    private val customerViewModel: CustomerViewModel by viewModels()

    private lateinit var binding: ActivityChooseCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_customer)

        customerViewModel.fetchUsers()
        customerViewModel.users.observe(this)
        { customers ->
            if (!customers.isNullOrEmpty()) {
                binding.userRecyclerView.adapter = UserListAdapter(customers)
                { selectedCustomer ->
                    Log.d(TAG, "onCreate:  ${selectedCustomer.name}")

                    }
            }
        }
    }
}