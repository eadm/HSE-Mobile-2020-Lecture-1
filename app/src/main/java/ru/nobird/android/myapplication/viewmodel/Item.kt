package ru.nobird.android.myapplication.viewmodel

import com.google.gson.annotations.SerializedName
import ru.nobird.android.core.model.Identifiable

data class Item(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String
) : Identifiable<Int>