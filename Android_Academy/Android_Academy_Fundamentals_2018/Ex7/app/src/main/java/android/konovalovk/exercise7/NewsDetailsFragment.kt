package android.konovalovk.exercise7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions

//var strForTitle:String = ""
//var strForFullText:String = ""
//var strForImgUrl:String = ""
//API KEY: jMWrVAIBwDSJWo1KevvBnLaw9ZUO11eH
class NewsDetailsFragment : Fragment() {
    private var imageLoader: RequestManager? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_news_details, container, false)
        val title: TextView = view.findViewById(R.id.detailsTitle)
        val fullText:TextView = view.findViewById(R.id.detailsText)
        val img:ImageView = view.findViewById(R.id.detailsImg)

        title.text = MainActivity.strForTitle
        fullText.text = MainActivity.strForFullText

        //With Activity
        //title.text = strForTitle
        //fullText.text = strForFullText
        imageLoader = Glide.with(view).applyDefaultRequestOptions(RequestOptions().centerCrop())
        //imageLoader!!.load(strForImgUrl)!!.into(img)

        imageLoader!!.load(MainActivity.strForImgUrl)!!.into(img)

        return view
    }
}
