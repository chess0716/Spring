package com.example.ccp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import com.example.ccp.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {

    // 뷰 바인딩 인스턴스 선언
    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replaceFragment(MyPostsFragment())


        binding.btnContent.setOnClickListener {
            replaceFragment(MyPostsFragment())
        }

        binding.btnHeart.setOnClickListener {
            replaceFragment(FavoritePostsFragment())
        }

        binding.btnMoney.setOnClickListener {
            replaceFragment(PaymentHistoryFragment())
        }
    }

    // 프래그먼트 교체를 위한 함수
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {

        }
    }
}
