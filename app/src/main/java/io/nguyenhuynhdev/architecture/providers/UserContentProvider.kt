package io.nguyenhuynhdev.architecture.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import io.nguyenhuynhdev.architecture.BuildConfig
import io.nguyenhuynhdev.architecture.app.data.database.AppDatabase
import io.nguyenhuynhdev.architecture.app.data.database.dao.UserDao
import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

// To use the UserContentProvider in another app, you can use ContentResolver class to perform CRUD operations on the user data:
//val contentValues = ContentValues().apply {
//    put("uid", 1)
//    put("first_name", "John")
//    put("last_name", "Doe")
//}
//val uri = context.contentResolver.insert(Uri.parse("content://io.nguyenhuynhdev.architecture.providers.UserContentProvider/user"), contentValues)
/*Here is how you can use the UserContentProvider in another app:

Add the following permission to your AndroidManifest.xml file:
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
Access the UserContentProvider using the following code:
kotlin
Copy code
val uri = Uri.parse("content://io.nguyenhuynhdev.architecture.providers.UserContentProvider/user")
val cursor = contentResolver.query(uri, null, null, null, null)
if (cursor!!.count > 0) {
    cursor.moveToFirst()
    do {
        val uid = cursor.getInt(cursor.getColumnIndex("uid"))
        val firstName = cursor.getString(cursor.getColumnIndex("first_name"))
        val lastName = cursor.getString(cursor.getColumnIndex("last_name"))
        Log.d("UserContentProvider", "uid: $uid, first_name: $firstName, last_name: $lastName")
    } while (cursor.moveToNext())
}
cursor.close()
Note: In the above code, replace "io.nguyenhuynhdev.architecture.providers.UserContentProvider" with your own content provider authority.*/
class UserContentProvider : ContentProvider() {

    private lateinit var userDao: UserDao
    private lateinit var appDatabase: AppDatabase

    companion object {
        const val USER = 1
        const val USERS = 2

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI("io.nguyenhuynhdev.architecture.providers", "user/#", USER)
            uriMatcher.addURI("io.nguyenhuynhdev.architecture.providers", "user", USERS)
        }
    }

    override fun onCreate(): Boolean {
        context?.let {
            appDatabase = Room.databaseBuilder(
                it, AppDatabase::class.java, BuildConfig.DB_NAME
            ).build()
            userDao = appDatabase.userDao()
            uriMatcher.addURI("io.nguyenhuynhdev.architecture", "user", USER)
        }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            USER -> {
                val id = uri.lastPathSegment!!.toInt()
                userDao.getUsersCursorById(id)
            }
            USERS -> {
                appDatabase.userDao().getUsersCursor()
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            USER -> "vnd.android.cursor.item/vnd.io.nguyenhuynhdev.architecture.providers.user"
            USERS -> "vnd.android.cursor.dir/vnd.io.nguyenhuynhdev.architecture.providers.user"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when (uriMatcher.match(uri)) {
            USER, USERS -> {
                values?.let {
                    val user = User(
                        uid = it.getAsInteger("uid"),
                        firstName = it.getAsString("first_name"),
                        lastName = it.getAsString("last_name")
                    )
                    context?.contentResolver?.notifyChange(uri, null)
                    userDao.insertAll(user).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe()
                    Uri.withAppendedPath(uri, user.uid.toString())
                }
            }
            else -> null
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            USER, USERS -> {
                uri.lastPathSegment?.let {
                    val uid = Integer.parseInt(it)
                    userDao.deleteByUid(uid)
                    context?.contentResolver?.notifyChange(uri, null)
                }
                1
            }
            else -> 0
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return when (uriMatcher.match(uri)) {
            USER, USERS -> {
                values?.let {
                    val user = User(
                        uid = it.getAsInteger("uid"),
                        firstName = it.getAsString("first_name"),
                        lastName = it.getAsString("last_name")
                    )
                    userDao.updateUser(user).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe()
                    context?.contentResolver?.notifyChange(uri, null)
                }
                1
            }
            else -> 0
        }
    }
}