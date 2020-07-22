package ru.nobird.android.myapplication.arch.domain.movie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.nobird.android.core.model.Identifiable

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    override val id: Int,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "cinema")
    @SerializedName("cinema_id")
    val cinemaId: Long
) : Identifiable<Int>