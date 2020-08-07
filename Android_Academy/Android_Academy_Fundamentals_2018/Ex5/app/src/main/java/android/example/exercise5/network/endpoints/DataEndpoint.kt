package android.example.exercise5.network.endpoints

import android.example.exercise5.network.DefaultResponse
import android.example.exercise5.network.dto.MultimediaDTO
import androidx.annotation.NonNull
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//TODO: Retrofit 3. Конечная точка + запрос.
interface DataEndpoint {
    //Endpoint - конечный путь в запросе, после которого идут параметры
    // тип запроса и Path to Endpoint

    @NonNull
    @GET("svc/topstories/v2/arts.json")
    //Что получаем в ответ. Этот Call еще много где будет встречать как и тип получаемого ответа
    fun search(): Call<DefaultResponse<List<MultimediaDTO>>>
    //Параметризованный запрос
    //fun search(@Query("") @NonNull search: String): Call<DefaultResponse<List<ImagesDTO>>>
}