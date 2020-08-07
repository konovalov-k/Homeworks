package android.konovalovk.exercise6.RoomDB

import android.content.Context
//ToDo: 4) Create Repository with helper fun
class FilmRepository(private val mContext: Context) {

    fun getData(): List<NewsEntity>?{
            val db = AppDatabase.getAppDatabase(mContext)
            return db?.filmDao?.all()
        }

    fun saveData(filmList: List<NewsEntity>) {
        val db = AppDatabase.getAppDatabase(mContext)
        db?.filmDao?.deleteAll()
        val films = filmList.toTypedArray()
        db?.filmDao?.insertAll(films)
    }

    fun dropData(){
        val db = AppDatabase.getAppDatabase(mContext)
        db?.filmDao?.deleteAll()
    }
}