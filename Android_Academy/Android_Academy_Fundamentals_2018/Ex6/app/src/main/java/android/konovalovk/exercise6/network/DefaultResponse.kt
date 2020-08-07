package android.konovalovk.exercise6exercise6.network

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

//Получаем неизвестно какой тип или верхний уровень в JSON
//Верхний список results
data class DefaultResponse <T>(
    @SerializedName("results") var results: T? = null
)
/*
Not data class
class DefaultResponse <T> {
    @SerializedName("results")
    var results: T? = null

Java
public class DefaultResponse<T> {

    private T data;

    @Nullable
    public T getData() {
        return data;
    }
}

 */