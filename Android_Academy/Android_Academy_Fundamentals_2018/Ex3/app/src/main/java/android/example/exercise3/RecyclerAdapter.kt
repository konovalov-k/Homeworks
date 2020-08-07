package android.example.exercise3

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

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    //TODO: Glide 01) Манагер, который решает че и куда.
    private var imageLoader: RequestManager? = null

    var data = DataUtils.generateNews()
        set(value) {
            field = value
            notifyDataSetChanged()}
    override fun getItemCount()= data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val someCategory: TextView = itemView.findViewById(R.id.category)
        val someTitle: TextView = itemView.findViewById(R.id.title)
        val someText: TextView = itemView.findViewById(R.id.info_text)
        val someImage: ImageView = itemView.findViewById(R.id.someImg)

        //TODO: 01) setOnClickListener and launch Activity.
        //Задаем берем контекст у итема и пихаем его в интент.
        val context:Context = itemView.context
        val intent:Intent = Intent(context, NewsDetailsActivity::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context) // берем инфлейтор из контекста родителя
        val view = layoutInflater
            //Че инфлейтим.
            .inflate(R.layout.some_item, parent,false) //в качестве вьюхи инфлейтим xml нашего айтема
        //TODO: Glide 02) Даем ему котнекст(куда пихать) и настройки. Можно создать объект типа RequestOptions и к нему уже настройки и его пихать.
        imageLoader = Glide.with(parent.context).applyDefaultRequestOptions(RequestOptions().centerCrop())

        return ViewHolder(view) //и возвращаем вьюхолдер, собранный из нашего айтема

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]//берем очередной элемент

        //TODO: 02) setOnClickListener and launch Activity.
        //Задаем онклик у каждого текста итема
        holder.someText.setOnClickListener {
            //holder.intent.putExtra("SAVEME","BITCH")

            strForTitle = item.title
            strForFullText = item.fullText
            strForImgUrl = item.imageUrl
            holder.context.startActivity(holder.intent)

            //holder.detailsFullText.text = item.fullText
            //holder.detailsTitle.text = item.title
            //imageLoader!!.load(item.imageUrl)!!.into(holder.detailsImg)
        }
        holder.someCategory.text = item.category.name
        holder.someTitle.text = item.title
        holder.someText.text = item.previewText
        //TODO: Glide 03) Берем ссылку из нашего итема и пихаем в нужную вью.
        imageLoader?.load(item.imageUrl)?.into(holder.someImage)
    }
}