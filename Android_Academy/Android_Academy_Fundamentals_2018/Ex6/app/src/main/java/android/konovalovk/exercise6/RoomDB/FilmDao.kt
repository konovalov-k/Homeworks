package android.konovalovk.exercise6.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

//ToDo: Room 2) Create DAO
@Dao
interface FilmDao {

    @Query("SELECT * FROM film")
    fun all(): List<NewsEntity>

    @Insert(onConflict = REPLACE)
    fun insertAll(films: Array<NewsEntity>)

    @Delete
    fun delete(film: NewsEntity)

    @Query("DELETE FROM film")
    fun deleteAll()

    /**
     * Selects and returns the film with given Name.
     */
    @Query("SELECT * FROM film WHERE title LIKE :title LIMIT 1")
    fun findByName(title: String): NewsEntity

    @Query("SELECT * FROM film WHERE title IN (:titles)")
    fun loadAllByTitles(titles: Array<String>): List<NewsEntity>

}

