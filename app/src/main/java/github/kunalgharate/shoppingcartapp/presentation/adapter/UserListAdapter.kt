package github.kunalgharate.shoppingcartapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.kunalgharate.shoppingcartapp.R
import github.kunalgharate.shoppingcartapp.data.model.Customer

class UserListAdapter(
    private val userList: List<Customer>,
    private val onUserSelected: (Customer) -> Unit
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        val isSelected = position == selectedPosition
        holder.bind(user, isSelected)

        holder.itemView.setOnClickListener {
            updateDate(position = position,user=user)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val radioButton: RadioButton = itemView.findViewById(R.id.userRadioButton)

        fun bind(user: Customer, isSelected: Boolean) {
            userNameTextView.text = user.name
            radioButton.isChecked = isSelected

            radioButton.setOnClickListener {
                    updateDate(position = position,user=user)
                }
            }
        }


    fun updateDate(position:Int,user: Customer)
    {
        if (selectedPosition == position) {
            // If the item is already selected, deselect it.
            selectedPosition = -1
            notifyItemChanged(position)
            onUserSelected(user) // You can pass null or a default value depending on your requirement
        } else {
            val previousSelectedPosition = selectedPosition
            selectedPosition = position
            if (previousSelectedPosition != -1) {
                notifyItemChanged(previousSelectedPosition)
            }
            notifyItemChanged(selectedPosition)
            onUserSelected(user)
        }
    }

}
