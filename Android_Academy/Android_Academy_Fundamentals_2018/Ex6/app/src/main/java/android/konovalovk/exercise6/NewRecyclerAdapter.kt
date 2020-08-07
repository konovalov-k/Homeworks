package android.konovalovk.exercise6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import android.content.Context
import android.content.Intent
import android.konovalovk.exercise6exercise6.network.dto.MultimediaDTO
import android.util.Log
import java.util.ArrayList

class NewRecyclerAdapter: RecyclerView.Adapter<NewRecyclerAdapter.ViewHolder>(){

    private var imageLoader: RequestManager? = null
    private var trueData:MutableList<NewNewsItem> = mutableListOf()
    override fun getItemCount() = trueData.size

    fun replaceItems(items: List<MultimediaDTO>) {
        //this.trueData.clear()
        Log.d("Adapter","last index: ${items.lastIndex}")
        //Converter from List to NewsItem obj
        for(i in items.indices){
            //Log.e("Recycler","${items[i].multimedia!!.size}")
            //Log.e("Recycler","${items[i].multimedia!![0].url}")

            this.trueData.add(
                NewNewsItem(
                    items[i].category,
                    items[i].title,
                    items[i].previewText,
                    //If
                    when(items[i].multimedia?.isEmpty()){
                        true-> ""
                        else -> items[i].multimedia!![0].url
                    }

                )
                        )
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val someCategory: TextView = itemView.findViewById(R.id.category)
        val someTitle: TextView = itemView.findViewById(R.id.title)
        val someText: TextView = itemView.findViewById(R.id.info_text)
        val someImage: ImageView = itemView.findViewById(R.id.someImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context) // берем инфлейтор из контекста родителя
        val view = layoutInflater
            //Че инфлейтим.
            .inflate(R.layout.some_item, parent,false) //в качестве вьюхи инфлейтим xml нашего айтема
        imageLoader = Glide.with(parent.context).applyDefaultRequestOptions(RequestOptions().centerCrop())

        return ViewHolder(view) //и возвращаем вьюхолдер, собранный из нашего айтема

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trueData[position]//берем очередной элемент

        //Задаем онклик у каждого текста итема
        /*holder.someText.setOnClickListener {
            //holder.intent.putExtra("SAVEME","BITCH")

            strForTitle = item.title
            strForFullText = item.fullText
            strForImgUrl = item.imageUrl
            holder.context.startActivity(holder.intent)

            //holder.detailsFullText.text = item.fullText
            //holder.detailsTitle.text = item.title
            //imageLoader!!.load(item.imageUrl)!!.into(holder.detailsImg)
        }*/
        holder.someCategory.text = item.category
        holder.someTitle.text = item.title
        holder.someText.text = item.previewText
        imageLoader?.load(item.imageUrl)?.into(holder.someImage)
    }
}