package android.konovalovk.exercise7.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//ToDo: Room 1) Объявляем имя дб и колонки
@Entity(tableName = "film")
data class NewsEntity(
    @PrimaryKey @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "previewText") var previewText: String? = null,
    @ColumnInfo(name = "url") var url: String? = null,
    @ColumnInfo(name = "category") var category:String? = null
)
