package io.nguyenhuynhdev.architecture.app.domain.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(
    @SerializedName("uid")
    @PrimaryKey val uid: Int,
    @SerializedName("first_name")
    @ColumnInfo(name = "first_name") val firstName: String?,
    @SerializedName("last_name")
    @ColumnInfo(name = "last_name") val lastName: String?
): Parcelable
