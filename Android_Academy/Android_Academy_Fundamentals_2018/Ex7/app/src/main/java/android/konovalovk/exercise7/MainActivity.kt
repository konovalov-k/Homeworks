package android.konovalovk.exercise7

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

lateinit var ft:FragmentManager

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"


companion object{
    var strForTitle: String? = null
    var strForFullText: String? = null
    var strForImgUrl: String? = null

    fun switchContent(fragment: Fragment) {
        strForTitle = fragment.arguments?.getString("strForTitle")
        strForFullText = fragment.arguments?.getString("strForFullText")
        strForImgUrl = fragment.arguments?.getString("strForImgUrl")
        ft
            .beginTransaction()
            //.add(R.id.frame_list, fragment)
            .replace(R.id.frame_list, fragment, fragment.toString())
            .addToBackStack(null)
            .commit()

            //.replace(id, fragment, fragment.toString())
            //.addToBackStack(null);
    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ft = supportFragmentManager
        val newsListFragment = NewsListFragment()
        val newsListDetailFragment = NewsDetailsFragment()
        // Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_list, newsListFragment)
            .commit()
    }


}
