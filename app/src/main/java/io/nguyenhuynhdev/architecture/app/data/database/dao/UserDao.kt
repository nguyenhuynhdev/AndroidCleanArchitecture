package io.nguyenhuynhdev.architecture.app.data.database.dao

import androidx.room.*
import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): Flowable<List<User>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: User): Completable

    @Delete
    fun delete(user: User)
}