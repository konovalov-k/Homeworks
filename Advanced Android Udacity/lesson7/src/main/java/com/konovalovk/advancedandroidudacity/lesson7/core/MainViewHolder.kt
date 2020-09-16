package com.konovalovk.advancedandroidudacity.lesson7.core

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.konovalovk.advancedandroidudacity.lesson7.R

class MainAdapter(private val data: List<Step>, private val navigateTo: (Int) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MainViewHolder(view as CardView, navigateTo)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

class MainViewHolder(private val cardView: CardView, val navigateTo: (Int) -> Unit) : RecyclerView.ViewHolder(cardView) {
    val header: TextView = cardView.findViewById(R.id.header)
    val description: TextView = cardView.findViewById(R.id.description)
    val caption: TextView = cardView.findViewById(R.id.caption)

    fun bind(step: Step) {
        header.text = step.number
        description.text = step.name
        caption.text = step.caption
        val context = cardView.context
        cardView.setOnClickListener {
            navigateTo(step.fragmentId)
        }
        val color = if (step.highlight) {
            context.resources.getColor(R.color.secondaryLightColor)
        } else {
            context.resources.getColor(R.color.primaryTextColor)
        }
        header.setTextColor(color)
        description.setTextColor(color)
    }

}
