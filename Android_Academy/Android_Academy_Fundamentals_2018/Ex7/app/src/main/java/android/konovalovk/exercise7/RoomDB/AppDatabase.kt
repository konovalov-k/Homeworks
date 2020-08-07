package android.konovalovk.exercise7.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//ToDo: Room 3) Create instance of DB
@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /*** Connects the database to the DAO. */

    abstract val filmDao: FilmDao

    //abstract fun filmAsyncDao(): FilmAsyncDao

    companion object {
        //Singleton will keep a reference to any database returned via getAppDatabase.
        //The value of a volatile variable will never be cached, and all writes and
        //reads will be done to and from the main memory. It means that changes made by one
        //thread to shared data are visible to other threads.
        @Volatile
        private var singleton: AppDatabase? = null

        private const val DATABASE_NAME = "FilmRoomDb.db"

        fun getAppDatabase(context: Context): AppDatabase? {
            // If instance is `null` make a new database instance.
            if (singleton == null) {
                synchronized(this) {
                    if (singleton == null) {
                        singleton = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, //DbClass
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration() // Wipes and rebuilds instead of migrating if no Migration object.
                            .build()
                    }
                }
            }
            // Return instance; smart cast to be non-null.
            return singleton
        }
    }
}