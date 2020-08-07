package android.konovalovk.exercise6exercise6.network.endpoints

import android.konovalovk.exercise6exercise6.network.DefaultResponse
import android.konovalovk.exercise6exercise6.network.dto.MultimediaDTO
import androidx.annotation.NonNull
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
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