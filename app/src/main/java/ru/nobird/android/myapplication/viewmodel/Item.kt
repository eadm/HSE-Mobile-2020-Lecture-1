package ru.nobird.android.myapplication.viewmodel

import ru.nobird.android.core.model.Identifiable

data class Item(
    override val id: Int
) : Identifiable<Int>