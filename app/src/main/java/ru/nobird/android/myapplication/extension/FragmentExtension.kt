package ru.nobird.android.myapplication.extension

import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.getTarget(): T? =
    (targetFragment as? T) ?:
    (parentFragment as? T) ?:
    (activity as? T)