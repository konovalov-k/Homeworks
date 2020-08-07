package android.example.exercise5

import android.example.exercise5.network.DefaultResponse
import android.example.exercise5.network.RestApi
import android.example.exercise5.network.dto.MultimediaDTO
import android.example.exercise5.network.dto.UrlDTO
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListActivity : AppCompatActivity() {
    //private var toolbar: Toolbar? = null
    //private var searchView: SearchView? = null
    private lateinit var recycler: RecyclerView
    private val photosAdapter = NewRecyclerAdapter()
    private var btnTryAgain: Button? = null
    private var viewError: View? = null
    private var viewLoading: View? = null
    private var viewNoData: View? = null
    private var tvError: TextView? = null
    //TODO: Retrofit 8.1 Объявляем наш реквест
    private var searchRequest: Call<DefaultResponse<List<MultimediaDTO>>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setupUi()
    }

    private fun setupUi() {
        findViews()
        setupRecyclerViews()
        //toolbar.setTitle(R.string.toolbar_search_tittle)

        //setupSearchView()
    }

    private fun setupRecyclerViews() {
        recycler.layoutManager = LinearLayoutManager(this)
        //TODO: Retrofit + RecyclerView 8.2- Вот это блин очень важно. Т.к. обратится к recycler.adapter мы не можем, но можем задать кастомный адаптер (photosAdapter) и уже с ним работать.
        recycler.adapter = photosAdapter//даем ему адаптер, с которым будем работать
    }
    private fun findViews() {
        recycler = findViewById(R.id.recycler)// ищем ресайклер
        //rvPhotos = findViewById(R.id.rv_photos)
        //toolbar = findViewById(R.id.toolbar)
        //btnTryAgain = findViewById(R.id.btn_try_again)
        //viewError = findViewById(R.id.lt_error)
        //viewLoading = findViewById(R.id.lt_loading)
        //viewNoData = findViewById(R.id.lt_no_data)
        //tvError = findViewById(R.id.tv_error)
    }
    override fun onStart() {
        super.onStart()
        //setupUx()
        //запуск нашей функции
        loadGifs()
    }

    private fun loadGifs() {
        //showState(State.Loading)
        //всяческие проверки searchRequest на null, isCanceled, isExecuted
        cancelCurrentRequestIfNeeded()

        //ToDO: 9) Retrofit: Вызов запроса
        searchRequest = RestApi.getInstance()?.data()?.search()

        searchRequest?.enqueue(object: Callback<DefaultResponse<List<MultimediaDTO>>> {
            override fun onResponse(
                @NonNull call: Call<DefaultResponse<List<MultimediaDTO>>>,
                @NonNull response: Response<DefaultResponse<List<MultimediaDTO>>>
            ) {
                checkResponseAndShowState(response)
            }

            override fun onFailure(
                @NonNull call: Call<DefaultResponse<List<MultimediaDTO>>>,
                @NonNull t: Throwable
            ) {
                Log.e("Baby","Baby don't hurt me",t)
            }
        })
    }

    private fun cancelCurrentRequestIfNeeded() {
        if (searchRequest == null) {
            return
        }

        //check if request already cancelled
        if (searchRequest!!.isCanceled) {
            searchRequest = null
            return
        }

        //check if request executed OR already in queue
        if (searchRequest!!.isExecuted) {
            searchRequest!!.cancel()
            searchRequest = null
        }
    }

    private fun checkResponseAndShowState(response:Response<DefaultResponse<List<MultimediaDTO>>>) {
        if (!response.isSuccessful) {
            //showState(State.ServerError)
            Log.d("NewList","Server error")
            return
        }

        val body:DefaultResponse<List<MultimediaDTO>>? = response.body()

        if (body == null) {
            //showState(State.HasNoData)
            Log.d("NewList","Body null")
            return
        }

        val results:List<MultimediaDTO>? = body.results
        if (results == null) {
            //showState(State.HasNoData)
            Log.d("NewList","Data null")
            return
        }

        if (results.isEmpty()) {
            //showState(State.HasNoData)
            Log.d("NewList","Data Empty")
            return
        }
        Log.d("NewList","photos triggered")
        photosAdapter.replaceItems(results)

        //showState(State.HasData)
    }


}
