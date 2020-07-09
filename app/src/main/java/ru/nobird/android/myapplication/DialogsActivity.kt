package ru.nobird.android.myapplication

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_dialogs.*
import ru.nobird.android.myapplication.dialog.AlertDialogFragment
import ru.nobird.android.myapplication.dialog.FullscreenDialogFragment
import ru.nobird.android.view.base.ui.extension.snackbar

class DialogsActivity : FragmentActivity(), AlertDialogFragment.Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogs)

        alertDialogButton.setOnClickListener {
            AlertDialogFragment
                .newInstance()
                .show(supportFragmentManager, AlertDialogFragment.TAG)
        }

        fullScreenDialogButton.setOnClickListener {
            FullscreenDialogFragment
                .newInstance()
                .show(supportFragmentManager, FullscreenDialogFragment.TAG)
        }
    }

    override fun onMobileDataStateChanged(isMobileAllowed: Boolean) {
        root.snackbar(message = isMobileAllowed.toString())
    }
}