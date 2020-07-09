package ru.nobird.android.myapplication.fragment.dynamic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import kotlinx.android.synthetic.main.activity_fragment_dynamic.*
import ru.nobird.android.myapplication.R

class FragmentDynamicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dynamic)

        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                // Динамическое добавление

                val fragmentOne = DynamicFragmentOne()
                add(R.id.container, fragmentOne, DynamicFragmentOne.TAG)

//            val fragmentTwo = DynamicFragmentTwo()
//            add(R.id.container, fragmentTwo, DynamicFragmentTwo.TAG)
//            replaceButton.isVisible = true
            }
        }

        // Замена фрагмента

        replaceButton.setOnClickListener {
            supportFragmentManager.commitNow {
                replace(R.id.container, DynamicFragmentTwo())
            }
        }
    }
}