package com.example.ccp.adapter



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ccp.model.IngrBoard
import com.example.ccp.R

class IngrBoardAdapter(
    private val context: Context,
    private var boards: MutableList<IngrBoard>,
    private val onItemStateChanged: (String, Boolean) -> Unit
) : RecyclerView.Adapter<IngrBoardAdapter.ViewHolder>() {

    constructor(context: Context) : this(context, mutableListOf(), { _, _ -> })


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val board = boards[position]
        holder.bind(board)
        holder.ingrGetSwitch.setOnCheckedChangeListener { _, isChecked ->
            val ingredientName = board.name ?: return@setOnCheckedChangeListener
            onItemStateChanged(ingredientName, isChecked)
        }

    }

    override fun getItemCount(): Int = boards.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingrNameTextView: TextView = itemView.findViewById(R.id.ingrName)
        val ingrUnitTextView: TextView = itemView.findViewById(R.id.ingrUnit)
        val ingrGetSwitch: Switch = itemView.findViewById(R.id.ingrGet)

        fun bind(board: IngrBoard) {
            ingrNameTextView.text = board.name
            ingrUnitTextView.text = board.unit.toString()
            ingrGetSwitch.isChecked = board.isOwned
        }
    }
}