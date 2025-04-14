package com.example.nit3213app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213app.api.Entity

class EntityAdapter(
    private val context: Context,
    private var list: List<Entity>,
    private val onClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {


    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val property1Text: TextView = itemView.findViewById(R.id.property1Text)
        val property2Text: TextView = itemView.findViewById(R.id.property2Text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val item = list[position]
        holder.property1Text.text = "Species: ${item.species}"
        holder.property2Text.text = "Scientific: ${item.scientificName}"
        //to make the card clickable
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<Entity>) {
        list = newList
        notifyDataSetChanged()
    }
}
