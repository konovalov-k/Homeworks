package android.konovalovk.exercise7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import android.konovalovk.exercise7.network.dto.MultimediaDTO
import android.util.Log
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.konovalovk.exercise7.MainActivity



class NewRecyclerAdapter: RecyclerView.Adapter<NewRecyclerAdapter.ViewHolder>(){

    private var imageLoader: RequestManager? = null
    private var trueData:MutableList<NewNewsItem> = mutableListOf(NewNewsItem("Oh Baby","I wanna see you","again",""))
    override fun getItemCount() = trueData.size
    var newsListDetailFragment = NewsDetailsFragment()
    var bundle = Bundle()

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

    class ViewHolder(itemView: View, parent: ViewGroup) : RecyclerView.ViewHolder(itemView){
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

        return ViewHolder(view,parent) //и возвращаем вьюхолдер, собранный из нашего айтема

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trueData[position]//берем очередной элемент

        //Задаем онклик у каждого текста итема
        holder.someText.setOnClickListener {
            //strForTitle = item.title!!
            //strForFullText = item.previewText!!
            //strForImgUrl = item.imageUrl!!

            //Запаковываем фрагмент
            bundle.putString("strForTitle",item.title)
            bundle.putString("strForFullText",item.previewText)
            bundle.putString("strForImgUrl",item.imageUrl)
            newsListDetailFragment.arguments = bundle

            MainActivity.switchContent(newsListDetailFragment)
        }

        holder.someCategory.text = item.category
        holder.someTitle.text = item.title
        holder.someText.text = item.previewText
        imageLoader?.load(item.imageUrl)?.into(holder.someImage)
    }
}