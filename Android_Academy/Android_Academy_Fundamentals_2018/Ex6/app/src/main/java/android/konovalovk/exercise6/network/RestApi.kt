package android.konovalovk.exercise6exercise6.network

import android.konovalovk.exercise6exercise6.network.endpoints.DataEndpoint
import androidx.annotation.NonNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestApi{
    companion object {
        private val API_KEY = "jMWrVAIBwDSJWo1KevvBnLaw9ZUO11eH"
        private val URL = "https://api.nytimes.com/"
        private val TIMEOUT_IN_SECONDS = 2
        private var sRestApi: RestApi? = null


        //Черная магия - синглтон.
        //Если есть есть инстанс то возвращает, если нет то создает новый
        fun getInstance(): RestApi?{
            if (sRestApi == null) {
                sRestApi = RestApi()
            }
            return sRestApi
        }
    }

    //Реализуем интерфейс с нашими данными
    //Интерфейс реализуется,а абстрактный класс наследуется
    private val dataEndpoint: DataEndpoint

    @NonNull
    private fun buildRetrofitClient(@NonNull client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @NonNull
    private fun buildOkHttpClient(): OkHttpClient {
        //Перехватчик запросов
        val networkLogInterceptor = HttpLoggingInterceptor()
        networkLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor.create(API_KEY))
            .addInterceptor(networkLogInterceptor)
            //Настройка таймаутов
            .connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .build()
    }


    init {
        val httpClient = buildOkHttpClient()
        val retrofit = buildRetrofitClient(httpClient)

        //init endpoints here. It's can be more then one endpoint
        dataEndpoint = retrofit.create(DataEndpoint::class.java)
    }
    fun data(): DataEndpoint = dataEndpoint
}
