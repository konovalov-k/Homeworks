package android.example.exercise5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions

var strForTitle:String = ""
var strForFullText:String = ""
var strForImgUrl:String = ""
//API KEY: jMWrVAIBwDSJWo1KevvBnLaw9ZUO11eH
class NewsDetailsActivity : AppCompatActivity() {
    private var imageLoader: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val title: TextView = findViewById(R.id.detailsTitle)
        val fullText:TextView = findViewById(R.id.detailsText)
        val img:ImageView = findViewById(R.id.detailsImg)

        title.text = strForTitle
        fullText.text = strForFullText
        imageLoader = Glide.with(this).applyDefaultRequestOptions(RequestOptions().centerCrop())
        imageLoader!!.load(strForImgUrl)!!.into(img)
    }
}
