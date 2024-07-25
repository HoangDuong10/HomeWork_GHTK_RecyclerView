package com.example.examplerecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.examplerecyclerview.R
import com.example.examplerecyclerview.databinding.ItemLoadingBinding
import com.example.examplerecyclerview.databinding.ItemOrderBinding
import com.example.examplerecyclerview.model.User

class OrderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_ORDER: Int = 1
        const val TYPE_LOADING: Int = 2
    }
    private var listOrder: MutableList<User> = mutableListOf()
    private var isLoadingAdd: Boolean = false
    class OrderViewHolder(private val itemOrderBinding : ItemOrderBinding) : RecyclerView.ViewHolder(itemOrderBinding.root){
        fun bind(user : User){
            itemOrderBinding.tvName.text = user.name
            user.image?.let { itemOrderBinding.ivAvata.setImageResource(it) }
            itemOrderBinding.tvOrderStatus.text =user.statusOrder
            if(user.isStatusUser){
                itemOrderBinding.tvUserStatus.text = "Đã chốt"
                itemOrderBinding.tvUserStatus.setTextColor(ContextCompat.getColor(itemOrderBinding.tvUserStatus.context,
                    R.color.green))
            }
            itemOrderBinding.tvPhoneNumber.text = user.phone
            itemOrderBinding.tvMoney.text = user.money
            itemOrderBinding.tvAddress.text = user.address
            itemOrderBinding.tvNote.text = user.note
        }
    }
    class LoadingViewHolder( itemLoadingBinding: ItemLoadingBinding) : RecyclerView.ViewHolder(itemLoadingBinding.root)

    override fun getItemViewType(position: Int): Int {
        if(position == listOrder.size -1 && isLoadingAdd){
            return TYPE_LOADING
        }
        return TYPE_ORDER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == TYPE_LOADING){
            val itemLoadingBinding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            LoadingViewHolder(itemLoadingBinding)
        }else{
            val itemOrderBinding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            OrderViewHolder(itemOrderBinding)
        }
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is OrderViewHolder){
            holder.bind(listOrder[position])
        }
    }

    fun addListOrder(data: List<User>) {
        val positionStart=listOrder.size
        listOrder.addAll(data)
        notifyItemRangeInserted(positionStart, data.size)

    }

    fun addFooterLoading() {
        isLoadingAdd = true
        listOrder.add(User())
        notifyItemInserted(listOrder.lastIndex)
    }

    fun remoteFooterLoading() {
        isLoadingAdd = false
        val position =listOrder.size-1
        listOrder.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clearListOrder() {
        val itemCount = listOrder.size
        listOrder.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

}