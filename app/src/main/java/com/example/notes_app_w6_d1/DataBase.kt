package com.example.notes_app_w6_d1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase( context: Context?) : SQLiteOpenHelper(context, "details.db", null, 2) {
    val sqLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, Note text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase!!.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(sqLiteDatabase)
    }

    fun saveData(note: String){
        val cv = ContentValues()
        cv.put("Note", note)
        sqLiteDatabase.insert("Notes", null, cv)
    }

    fun retrieveData(): ArrayList<Note> {
        val notes: ArrayList<Note> = ArrayList()
        var c: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM Notes", null)
        if (c.count < 1){ println("DataBase not found") }
        else {
            while (c.moveToNext()){
                val id = c.getInt(0)
                val note = c.getString(1)
                notes.add(Note(id, note))
            }
        }
        return notes
    }
}