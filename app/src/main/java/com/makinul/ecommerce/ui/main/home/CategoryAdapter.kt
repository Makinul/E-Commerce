package com.makinul.ecommerce.ui.main.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.makinul.ecommerce.R
import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.databinding.RowCategoryBinding

class CategoryAdapter(
    private var arrayList: ArrayList<Category>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = RowCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    private fun getItem(position: Int): Category {
        return arrayList[position]
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val holder = viewHolder as ViewHolder
        with(holder) {
            this.bindData(item)
        }
    }

    inner class ViewHolder(
        private val binding: RowCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClickListener.onItemClick(it, layoutPosition, getItem(layoutPosition))
            }
        }

        fun bindData(item: Category) {
//            binding.icon
//            binding.date.text = booking.fromDateTime
//            val destination = booking.startingFrom + ", " + booking.via + ", " + booking.destination
//            binding.destination.text = destination
//
//            val transportType = BookingFragment.transportTypeMap[booking.transportationTypeID]
//            val vehicleType = BookingFragment.vehicleTypeMap[booking.vehicleTypeID]
//
//            var transportTypeS = "Transport info: "
//            if (transportType != null) {
//                transportTypeS += transportType.transportName
//            }
//
//            if (vehicleType != null) {
//                transportTypeS += "(" + vehicleType.vehicleName + ")"
//            }
//            binding.transportType.text = transportTypeS
//
//            val volume = "Volume ($layoutPosition)"
//            binding.volume.text = volume
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface OnClickListener {
        fun onItemClick(view: View, position: Int, item: Category)
    }

    @JvmOverloads
    fun showToast(context: Context, @StringRes resourceId: Int = R.string.work_in_progress) {
        showToast(context, context.getString(resourceId))
    }

    private fun showToast(context: Context, message: String) {
        if (message.isEmpty()) return
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLog(message: String = "Test") {
        Log.v("RequestAdapter", message)
    }
}