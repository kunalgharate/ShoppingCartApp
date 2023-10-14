package github.kunalgharate.shoppingcartapp.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import github.kunalgharate.shoppingcartapp.R
import github.kunalgharate.shoppingcartapp.constants.AppConstants
import github.kunalgharate.shoppingcartapp.data.model.Customer
import github.kunalgharate.shoppingcartapp.databinding.ActivityChooseCustomerBinding
import github.kunalgharate.shoppingcartapp.presentation.adapter.UserListAdapter
import github.kunalgharate.shoppingcartapp.presentation.vm.CustomerViewModel
import kotlin.properties.Delegates


@AndroidEntryPoint
class ChooseCustomerActivity : AppCompatActivity() {
    var customer : Customer? = null

    companion object {
        const val TAG = "ChooseCustomerActivity"
    }

    private val customerViewModel: CustomerViewModel by viewModels()

    private lateinit var binding: ActivityChooseCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_customer)

        //fetching users
        customerViewModel.fetchUsers()

        // observer users
        customerViewModel.users.observe(this)
        { customers ->
            if (!customers.isNullOrEmpty()) {
                binding.userRecyclerView.adapter = UserListAdapter(customers)
                { selectedCustomer ->
                    customer = selectedCustomer

                }
            }
        }
        // starting product activity
        binding.continueBtn.setOnClickListener {
            if (customer!=null) startProductActivity(customer!!) else Toast.makeText(this,"Please select customer",Toast.LENGTH_LONG).show()
        }
    }

    fun startProductActivity(customer: Customer) {

        val intent = Intent(this@ChooseCustomerActivity, MainActivity::class.java)
        intent.putExtra(AppConstants.customer, customer)
        startActivity(intent)
    }
}