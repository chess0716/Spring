package com.example.ccp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccp.adapter.IngrBoardAdapter
import com.example.ccp.databinding.ActivityDetailBinding
import com.example.ccp.model.BoardDTO
import com.example.ccp.model.IngrBoard
import com.example.ccp.service.ApiService
import com.example.ccp.service.UpdatePriceRequest
import com.example.ccp.util.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var apiService: ApiService
    private var totalPrice: Int = 0
    private val boards = mutableListOf<IngrBoard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = RetrofitClient.apiService

        val num = intent.getIntExtra("board_id", -1)
        if (num != -1) {
            loadData(num)
            loadIngredients(num)
            loadTotalPrice(num)
        }

        setupListeners()
    }

    private fun loadData(num: Int) {
        apiService.getBoardByNum(num).enqueue(object : Callback<BoardDTO> {
            override fun onResponse(call: Call<BoardDTO>, response: Response<BoardDTO>) {
                if (response.isSuccessful) {
                    val board = response.body()
                    board?.let {
                        binding.detailTitle.text = Editable.Factory.getInstance().newEditable(it.title)
                        binding.detailWriter.text = Editable.Factory.getInstance().newEditable(it.writer?.name ?: "Unknown")
                        binding.detailContent.text = Editable.Factory.getInstance().newEditable(it.content)
                    }
                } else {
                    Log.e("DetailActivity", "Failed to load board details, response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<BoardDTO>, t: Throwable) {
                Log.e("DetailActivity", "Failed to load board details: ${t.message}", t)
            }
        })
    }

    private fun loadIngredients(num: Int) {
        apiService.getIngredientsForBoard(num).enqueue(object : Callback<List<IngrBoard>> {
            override fun onResponse(call: Call<List<IngrBoard>>, response: Response<List<IngrBoard>>) {
                response.body()?.let {
                    boards.clear()
                    boards.addAll(it)
                    setupRecyclerView()
                }
            }

            override fun onFailure(call: Call<List<IngrBoard>>, t: Throwable) {
                Log.e("DetailActivity", "Failed to load ingredients: ${t.message}", t)
            }
        })
    }

    private fun loadTotalPrice(num: Int) {
        apiService.getTotalPrice(num).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    val totalPrice = response.body()
                    totalPrice?.let {
                        val formattedPrice = "$it 원"
                        binding.totalPrice.text = Editable.Factory.getInstance().newEditable(formattedPrice)
                    }
                } else {
                    Log.e("DetailActivity", "Failed to load total price, response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("DetailActivity", "Failed to load total price: ${t.message}", t)
            }
        })
    }

    private fun setupRecyclerView() {

        val onSwitchChanged: (String, Boolean) -> Unit = { ingredientName, isOwned ->
            val boardNum = intent.getIntExtra("board_id", -1)
            if (boardNum != -1) {
                val requestBody = UpdatePriceRequest(ingredientName, isOwned)
                updateTotalPrice(boardNum, requestBody)
            }
        }

        // 어댑터 인스턴스 생성 시 콜백 함수 전달
        val adapter = IngrBoardAdapter(this, boards, onSwitchChanged)
        binding.recyclerViewIngredients.adapter = adapter
        binding.recyclerViewIngredients.layoutManager = LinearLayoutManager(this)
    }


    private fun updateTotalPrice(boardNum: Int, requestBody: UpdatePriceRequest) {
        apiService.updatePrice(boardNum, requestBody).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    val updatedPrice = response.body() ?: return
                    val formattedPrice = "$updatedPrice 원"
                    binding.totalPrice.text = Editable.Factory.getInstance().newEditable(formattedPrice)
                } else {
                    Log.e("DetailActivity", "Failed to update total price, response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("DetailActivity", "Failed to update total price: ${t.message}", t)
            }
        })
    }




    private fun setupListeners() {
        binding.btnBack.setOnClickListener { finish() }
        binding.btnGoUpdate.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            // 필요한 데이터 인텐트에 추가
            startActivity(intent)
        }
    }
}