package com.example.eshopping

import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.text.TextUtils
import java.lang.IllegalArgumentException
import java.util.HashMap

class ShopCart() : ContentProvider() {
    companion object {
        val PROVIDER_NAME = "com.example.MyApplication.ShoppingCart"
        val URL = "content://" + PROVIDER_NAME + "/students"
        val CONTENT_URI = Uri.parse(URL)
        val NO ="num"
        val NAME = "name"
        val PRICE = "price"
        private val SHOPCART_PROJECTION_MAP: HashMap<String, String>? = null
        val NUM = 1
        val NUM2 = 2
        val uriMatcher: UriMatcher? = null
        val DATABASE_NAME = "Shop"
        val STUDENTS_TABLE_NAME = "ShopCart"
        val DATABASE_VERSION = 1
        val CREATE_DB_TABLE = " CREATE TABLE " + STUDENTS_TABLE_NAME +
                " (num INTEGER PRIMARY KEY AUTOINCREMENT, " + " name TEXT NOT NULL, " +
                " price TEXT NOT NULL);"
        private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH);
        init
        {

            sUriMatcher.addURI(PROVIDER_NAME, "ShopCart", NUM);
            sUriMatcher.addURI(PROVIDER_NAME, "ShopCart/#", NUM2);
        }

    }
    private var db: SQLiteDatabase? = null

    private class DatabaseHelper internal constructor(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME)
            onCreate(db)
        }
    }

    override fun onCreate(): Boolean {
        val context = context
        val dbHelper = DatabaseHelper(context)

        db = dbHelper.writableDatabase
        return if (db == null) false else true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        val rowID = db!!.insert(STUDENTS_TABLE_NAME, "", values)
        if (rowID > 0) {
            val _uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }
        throw SQLException("Failed to add a record into $uri")
    }

    override fun query(
        uri: Uri, projection: Array<String>?,
        selection: String?, selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var sortOrder = sortOrder
        val qb = SQLiteQueryBuilder()
        qb.tables = STUDENTS_TABLE_NAME
        if (uriMatcher != null) {
            when (uriMatcher.match(uri)) {
                /* STUDENTS -> qb.projectionMap =
                    STUDENTS_PROJECTION_MAP */
                NUM -> qb.appendWhere(NO + "=" + uri.pathSegments[1])
                else -> {null
                }
            }
        }


        if (sortOrder == null || sortOrder === "") {

            sortOrder = NAME
        }
        val c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder)

        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var count = 0
        when (uriMatcher!!.match(uri)) {
            NUM -> count = db!!.delete(
                STUDENTS_TABLE_NAME, selection,
                selectionArgs
            )
            NUM2 -> {
                val id = uri.pathSegments[1]
                count = db!!.delete(
                    STUDENTS_TABLE_NAME,
                    NO + " = " + NO +
                            if (!TextUtils.isEmpty(selection)) " AND ($selection)" else "",
                    selectionArgs
                )
            }
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        var count = 0
        when (uriMatcher!!.match(uri)) {
            NUM -> count = db!!.update(
                STUDENTS_TABLE_NAME, values, selection,
                selectionArgs
            )
            NUM2 -> count = db!!.update(
                STUDENTS_TABLE_NAME,
                values,
                NO + " = " + uri.pathSegments[1] + (if (!TextUtils.isEmpty(selection)) " AND ($selection)" else ""),
                selectionArgs
            )
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher!!.match(uri)) {
            NUM -> return "vnd.android.cursor.dir/vnd.example.students"
            NUM2 -> return "vnd.android.cursor.item/vnd.example.students"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }
}