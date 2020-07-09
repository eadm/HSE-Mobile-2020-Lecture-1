package ru.nobird.android.myapplication.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_navigation.*
import ru.nobird.android.myapplication.R

class NavigationFourFragment : Fragment() {
    companion object {
        const val TAG = "NavigationFourFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_navigation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationText.text = 4.toString()
    }
}