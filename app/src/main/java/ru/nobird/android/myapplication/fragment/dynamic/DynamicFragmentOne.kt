package ru.nobird.android.myapplication.fragment.dynamic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dynamic.*
import ru.nobird.android.myapplication.R

class DynamicFragmentOne : Fragment() {
    companion object {
        const val TAG = "DynamicFragmentOne"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_dynamic, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dynamicText.text = TAG
    }
}