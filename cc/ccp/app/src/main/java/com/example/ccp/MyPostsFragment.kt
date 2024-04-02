package com.example.ccp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccp.adapter.MyPageAdapter
import com.example.ccp.databinding.FragmentMyPostsBinding
import com.example.ccp.model.BoardDTO
import com.example.ccp.service.ApiService
import com.example.ccp.service.MyPageService
import com.example.ccp.util.RetrofitClient
import com.example.ccp.util.SharedPreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPostsFragment : Fragment() {

    private var _binding: FragmentMyPostsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MyPageAdapter
    private lateinit var myPageService: MyPageService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMyPostsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Retrofit 서비스 초기화
        myPageService = RetrofitClient.myPageService

        // 사용자 게시물 로드
        loadUserPosts()

        return view
    }

    private fun loadUserPosts() {
        // SharedPreferences에서 로그인한 사용자의 ID 가져오기
        val userId = SharedPreferencesHelper.getUserId(requireContext())

        Log.d("Fragment", "Logged in as: $userId")
        if (userId == -1L) {
            Toast.makeText(context, "사용자 ID를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        // 사용자의 게시물 가져오기
        myPageService.getUserPosts(userId).enqueue(object : Callback<List<BoardDTO>> {
            override fun onResponse(call: Call<List<BoardDTO>>, response: Response<List<BoardDTO>>) {
                if (response.isSuccessful) {
                    val userPosts = response.body()
                    userPosts?.let {
                        displayUserPosts(it)
                    }
                } else {
                    Toast.makeText(context, "게시물을 가져오는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<BoardDTO>>, t: Throwable) {
                Toast.makeText(context, "네트워크 오류가 발생했습니다: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun displayUserPosts(posts: List<BoardDTO>) {
        adapter = MyPageAdapter(posts)
        binding.recyclerViewMyPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMyPosts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
