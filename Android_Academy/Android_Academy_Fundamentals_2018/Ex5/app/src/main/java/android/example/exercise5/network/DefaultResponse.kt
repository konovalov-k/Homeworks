package android.example.exercise5.network

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

//TODO: Retrofit 1. Добавляем стандартный ответ
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