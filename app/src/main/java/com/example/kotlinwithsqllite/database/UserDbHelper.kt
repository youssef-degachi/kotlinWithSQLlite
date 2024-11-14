package com.example.kotlinwithsqllite.database


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "User.db"
        private const val SQL_CREATE_ENTRIES = """
            CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (
            ${UserContract.UserEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY,
            ${UserContract.UserEntry.COLUMN_NAME_NAME} TEXT,
            ${UserContract.UserEntry.COLUMN_NAME_AGE} INTEGER)
        """
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${UserContract.UserEntry.TABLE_NAME}"
    }
}