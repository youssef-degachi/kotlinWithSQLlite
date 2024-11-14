package com.example.kotlinwithsqllite.database


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log

class UserController(context: Context) {
    private val dbHelper = UserDbHelper(context)

    // Create a new user
    fun createUser(name: String, age: Int) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(UserContract.UserEntry.COLUMN_NAME_NAME, name)
            put(UserContract.UserEntry.COLUMN_NAME_AGE, age)
        }
        val newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values)
        Log.d("UserController", "User created with ID: $newRowId")
    }

    // Update an existing user
    fun updateUser(id: Int, name: String, age: Int) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(UserContract.UserEntry.COLUMN_NAME_NAME, name)
            put(UserContract.UserEntry.COLUMN_NAME_AGE, age)
        }
        val selection = "${UserContract.UserEntry.COLUMN_NAME_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val count = db.update(UserContract.UserEntry.TABLE_NAME, values, selection, selectionArgs)
        Log.d("UserController", "User updated. Rows affected: $count")
    }

    // Retrieve a user by ID
    fun getUser(id: Int): User? {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            UserContract.UserEntry.COLUMN_NAME_ID,
            UserContract.UserEntry.COLUMN_NAME_NAME,
            UserContract.UserEntry.COLUMN_NAME_AGE
        )
        val selection = "${UserContract.UserEntry.COLUMN_NAME_ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor: Cursor = db.query(
            UserContract.UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var user: User? = null
        with(cursor) {
            if (moveToFirst()) {
                val userId = getInt(getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_ID))
                val userName = getString(getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_NAME))
                val userAge = getInt(getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_AGE))
                user = User(userId, userName, userAge)
                Log.d("UserController", "User fetched: $user")
            }
            close()
        }
        return user
    }

    // Delete a user by ID
    fun deleteUser(id: Int) {
        val db = dbHelper.writableDatabase
        val selection = "${UserContract.UserEntry.COLUMN_NAME_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val deletedRows = db.delete(UserContract.UserEntry.TABLE_NAME, selection, selectionArgs)
        Log.d("UserController", "User deleted. Rows affected: $deletedRows")
    }

    // Data class for representing a User object
    data class User(val id: Int, val name: String, val age: Int)
}
