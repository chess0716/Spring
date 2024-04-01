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
    private var boardNum: Int = -1 // 보드 번호 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = RetrofitClient.apiService

        boardNum = intent.getIntExtra("board_id", -1) // 보드 번호 초기화

        if (boardNum != -1) {
            loadData(boardNum)

            loadTotalPrice(boardNum)
            loadIngredients(boardNum)
        }

        setupRecyclerView()
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
                    Log.e("DetailActivity", "게시글 정보를 불러오는데 실패했습니다. 응답코드: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<BoardDTO>, t: Throwable) {
                Log.e("DetailActivity", "게시글 정보를 불러오는데 실패했습니다: ${t.message}", t)
            }
        })
    }

    private fun loadIngredients(num: Int) {
        apiService.getIngredientsForBoard(num).enqueue(object : Callback<List<IngrBoard>> {
            override fun onResponse(call: Call<List<IngrBoard>>, response: Response<List<IngrBoard>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        boards.clear()
                        boards.addAll(it)
                        binding.recyclerViewIngredients.adapter?.notifyDataSetChanged()
                    }
                } else {
                    Log.e("DetailActivity", "재료 정보를 불러오는데 실패했습니다. 응답코드: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<IngrBoard>>, t: Throwable) {
                Log.e("DetailActivity", "재료 정보를 불러오는데 실패했습니다: ${t.message}", t)
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
                    Log.e("DetailActivity", "총 가격을 불러오는데 실패했습니다. 응답코드: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("DetailActivity", "총 가격을 불러오는데 실패했습니다: ${t.message}", t)
            }
        })
    }

    private fun setupRecyclerView() {
        val adapter = IngrBoardAdapter(
            this@DetailActivity,
            boards,
            { ingredientName, isOwned ->
                // 재료 상태 변경 시 로직 처리
                val position = boards.indexOfFirst { it.name == ingredientName }
                if (position != -1) {
                    boards[position].isOwned = isOwned
                }
            },
            { boardNum, requestBody ->
                // 총 가격 업데이트 로직 호출
                updateTotalPrice(boardNum, requestBody)
            },
            boardNum
        )
        binding.recyclerViewIngredients.adapter = adapter
        binding.recyclerViewIngredients.layoutManager = LinearLayoutManager(this)
    }
    private fun updateTotalPrice(boardNum: Int, requestBody: UpdatePriceRequest) {
        Log.d("DetailActivity", "Updating price for board $boardNum with ${requestBody.ingredientName}, owned: ${requestBody.isOwned}")
        apiService.updatePrice(boardNum, requestBody).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    val updatedPrice = response.body() ?: return
                    runOnUiThread {
                        // UI 업데이트
                        binding.totalPrice.text = Editable.Factory.getInstance().newEditable("$updatedPrice 원")
                    }
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
            val intent = Intent(this@DetailActivity, UpdateActivity::class.java)
            startActivity(intent)
        }
    }
}
