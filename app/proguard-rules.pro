# This file optimizes the output of the application by removing unused code
# and minimizing the size of the APK.

# Optimizations to apply to code and resources.
-optimizations code/shrink
-optimizations code/simplification/cast
-optimizations code/simplification/string
-optimizations code/simplification/field

# Keep classes and methods that are referenced by Android resources.
-keep class **.R
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Keep the entry point to the application.
-keepclassmembers class io.nguyenhuynhdev.architecture.AndroidApplication {
    public void onCreate();
}

# Keep the classes and methods used by the application.
-keep class io.nguyenhuynhdev.architecture.** { *; }
-keepclassmembers class io.nguyenhuynhdev.architecture.** {
    *;
}

# Kotlin
-keepclassmembers class kotlin.Metadata {
    public <methods>;
    public <fields>;
}
-keep class kotlin.reflect.** { *; }

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# RxJava
-dontwarn io.reactivex.**
-keep class io.reactivex.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Room
-keep class androidx.room.RoomOpenHelper { *; }
-keep class androidx.room.RoomDatabase { *; }
#-keep class androidx.room.paging.PagingSource { *; }
-keep class androidx.room.Entity { *; }
-keep class androidx.room.PrimaryKey { *; }
-keep class androidx.room.TypeConverters { *; }
-keep class androidx.room.migration.Migration { *; }
-keep class androidx.sqlite.db.SupportSQLiteDatabase { *; }
-keep class androidx.sqlite.db.SupportSQLiteOpenHelper { *; }
-keep class androidx.sqlite.db.SimpleSQLiteQuery { *; }
#-keep class androidx.sqlite.db.SimpleSQLiteQuery$ArgumentTypes { *; }
-keep class androidx.sqlite.db.SupportSQLiteStatement { *; }

# Hilt
-keep class dagger.hilt.** { *; }
-keepattributes Annotation
-keepattributes Signature
-keepclasseswithmembers class * {
    @dagger.hilt.* <methods>;
}

# Obfuscate the package names of classes.
-flattenpackagehierarchy