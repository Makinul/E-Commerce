package com.makinul.ecommerce.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.makinul.ecommerce.data.model.Employee
import com.makinul.ecommerce.databinding.ItemEmployeeBinding

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    inner class EmployeeViewHolder(itemBinding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        internal val tvName: TextView = itemBinding.tvName
        internal val tvSalary: TextView = itemBinding.tvSalary
        internal val tvAge: TextView = itemBinding.tvAge
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Employee>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemBinding: ItemEmployeeBinding = ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EmployeeViewHolder(itemBinding);
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.itemView.apply {
            holder.tvName.text = "${item.employee_name}"
            holder.tvSalary.text = "Salary: Rs.${item.employee_salary}"
            holder.tvAge.text = "Age: ${item.employee_age}"
        }
    }
}