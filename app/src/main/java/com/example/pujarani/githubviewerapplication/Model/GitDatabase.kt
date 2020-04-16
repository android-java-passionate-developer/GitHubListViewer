package com.example.pujarani.githubviewerapplication.Model

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.content.Context

/**
 * Created by Puja.Rani on 15-04-2020.
 */
@Database(entities = arrayOf(GitHubDatModel::class), version = 1, exportSchema = false)
abstract class GitDatabase : RoomDatabase() {

    abstract fun gitHubDao(): GitHubDao


    companion object {
        @Volatile
        private var INSTANCE: GitDatabase? = null

        fun getDatabase(context: Context): GitDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        GitDatabase::class.java,
                        "GitDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllTables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}