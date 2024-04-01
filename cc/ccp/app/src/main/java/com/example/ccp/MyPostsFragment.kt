package com.example.ccp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccp.adapter.MyPageAdapter
import com.example.ccp.databinding.FragmentMyPostsBinding
import com.example.ccp.model.BoardDTO
import com.example.ccp.service.ApiService
import com.example.ccp.service.MyPageService
import com.example.ccp.util.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPostsFragment : Fragment() {

    private var _binding: FragmentMyPostsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MyPageAdapter
    private lateinit var apiService: ApiService
    private lateinit var myPageService: MyPageService
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPostsBinding.inflate(inflater, container, false)
        val view = binding.root

        myPageService = RetrofitClient.myPageService

        loadUserPosts()

        return view
    }

    private fun loadUserPosts() {
        val userId = getUserId()
        myPageService.getUserPosts(userId).enqueue(object : Callback<List<BoardDTO>> {
            override fun onResponse(call: Call<List<BoardDTO>>, response: Response<List<BoardDTO>>) {
                if (response.isSuccessful) {
                    val userPosts = response.body()
                    userPosts?.let {
                        displayUserPosts(it)
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<List<BoardDTO>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun displayUserPosts(posts: List<BoardDTO>) {
        adapter = MyPageAdapter(posts)
        binding.recyclerViewMyPosts.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewMyPosts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUserId(): Long {
        // Implement a function to get user ID
        return 1 // Sample user ID
    }
}
