package com.example.examplerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examplerecyclerview.adapter.OrderAdapter
import com.example.examplerecyclerview.databinding.ActivityMainBinding
import com.example.examplerecyclerview.model.User
import com.example.examplerecyclerview.widget.PaginationScrollListener

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var linearLayoutManager : LinearLayoutManager
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var currentPage = 1
    private var totalPage = 4
    private lateinit var orderAdapter : OrderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        orderAdapter = OrderAdapter()
        linearLayoutManager = LinearLayoutManager(this)
        activityMainBinding.rcvOrder.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        activityMainBinding.rcvOrder.addItemDecoration(dividerItemDecoration)
        orderAdapter.addListOrder(getListOrder())
        activityMainBinding.rcvOrder.adapter =orderAdapter
        loadMore()
        refreshData()
    }

    private fun loadMore(){
        activityMainBinding.rcvOrder.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
            override fun loadMoreItem() {
                orderAdapter.addFooterLoading()
                isLoading = true
                currentPage += 1
                loadNextPage()
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

        })
    }

    private fun getListOrder(): List<User> {
        val list = mutableListOf<User>()
       for(i in 1..4){
            list.add(User("Dương",R.drawable.ic_avata,"0535639561","ĐH chưa cập nhật,để xử lí vui lòng đẩy lại",
                true,true,"Quận Đống Đa, Hà Nội","Test đơn hàng","124.000 đ"))
            list.add(User("Tùng",R.drawable.ic_avata1,"0435593539","ĐH 546754688",
                false,true,"Quận Hai Bà Trưng, Hà Nội","Test đơn hàng","34.000 đ"))
            list.add(User("Xuân Toàn",R.drawable.ic_avata2,"0984234245","ĐH 453455656",
                true,true,"Quận Thanh Xuân, Hà Nội","Test đơn hàng","68.000 đ"))
            list.add(User("Văn Hưng",R.drawable.ic_avata1,"01254655667","ĐH chưa cập nhật,để xử lí vui lòng đẩy lại",
                true,false,"Huyện Cần Giờ, TP Hồ Chí Minh","Test đơn hàng","70.000 đ"))
            list.add(User("Thắng",R.drawable.ic_avata2,"0565433789","ĐH 32433545",
                false,false,"Quận Đống Đa, Hà Nội","Test đơn hàng","14.000 đ"))
        }
        return list
    }

    private fun loadNextPage(){
        Handler(Looper.getMainLooper()).postDelayed({
            orderAdapter.remoteFooterLoading()
            orderAdapter.addListOrder(getListOrder())
            isLoading = false
            if(currentPage >= totalPage){
                isLastPage = true
            }
        },2000)
    }

    private fun refreshData() {
        activityMainBinding.srListOrder.setOnRefreshListener {
            currentPage = 1
            orderAdapter.clearListOrder()
            orderAdapter.addListOrder(getListOrder())
            activityMainBinding.srListOrder.isRefreshing = false
            isLastPage = false
        }
    }
}