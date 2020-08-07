package android.example.exercise5.network.dto

import com.google.gson.annotations.SerializedName

//DTO: Data Transfer Object

//TODO: Retrofit 2. Дата классы для наших объектов из JSON

// внимательно нужно указывать где объект, а где лист
// Конкретно тут уровень вложенности таков: results{}->multimedia{}->url
//т.е. 2 листа и 1 объект
data class MultimediaDTO (
    //TODO: Retrofit 2.2 Продолжаем
    //Inside result
    //If we have SerializedName annotation, SerializedName must be equal as in json,
    // var name may be anything
    //without annotation var MUST be equal as in json
    @SerializedName("multimedia") var multimedia:List<UrlDTO>? = null,
    @SerializedName("title") var title:String? = null,
    @SerializedName("abstract") var previewText:String? = null,
    @SerializedName("section") var category:String? = null
    )
//TODO: Retrofit 2.1 Начинаем с самого низкого уровня
data class UrlDTO(@SerializedName("url") var url:String? = null)

/*
Java from Lecture
class OriginalDTO {

    private String url;

    public String getUrl() {
        return url;
    }
}


class ImagesDTO {

    private OriginalDTO original;

    public OriginalDTO getOriginal() {
        return original;
    }
}

public class GifDTO {

    private ImagesDTO images;

    public String getUrl() {
        return images.getOriginal().getUrl();
    }
}

 */