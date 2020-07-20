package ru.nobird.android.myapplication.viewmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.nobird.android.core.model.Identifiable

@Entity(tableName = "cinemas")
data class Cinema(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    override val id: Long,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String
) : Identifiable<Long>