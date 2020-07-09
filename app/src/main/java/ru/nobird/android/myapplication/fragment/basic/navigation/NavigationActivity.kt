package ru.nobird.android.myapplication.fragment.basic.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_navigation.*
import ru.nobird.android.myapplication.R

class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        navigationView.setOnNavigationItemSelectedListener {
            setFragment(it.itemId)
            true
        }
        setFragment(R.id.one)
    }

    private fun getNextFragmentTag(@IdRes menuId: Int): String =
        when (menuId) {
            R.id.one ->
                NavigationOneFragment::class.java.simpleName

            R.id.two ->
                NavigationTwoFragment::class.java.simpleName

            R.id.three ->
                NavigationThreeFragment::class.java.simpleName

            R.id.four ->
                NavigationFourFragment::class.java.simpleName

            else ->
                throw IllegalStateException()
        }

    private fun setFragment(@IdRes id: Int) {
        val fragmentTag = getNextFragmentTag(id)

        supportFragmentManager.commit {
            supportFragmentManager.fragments.forEach { hide(it) }
            val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
            if (fragment != null) {
                show(fragment)
            } else {
                val nextFragment = getNextFragmentInstance(id)
                add(R.id.frame, nextFragment, nextFragment::class.java.simpleName)
            }
        }
    }

    private fun getNextFragmentInstance(@IdRes menuId: Int): Fragment =
        when (menuId) {
            R.id.one ->
                NavigationOneFragment()

            R.id.two ->
                NavigationTwoFragment()

            R.id.three ->
                NavigationThreeFragment()

            R.id.four ->
                NavigationFourFragment()

            else ->
                throw IllegalStateException()
        }
}