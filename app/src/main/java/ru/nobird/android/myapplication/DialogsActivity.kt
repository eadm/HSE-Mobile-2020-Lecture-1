package ru.nobird.android.myapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dialogs.*
import ru.nobird.android.myapplication.dialog.AlertDialogFragment
import ru.nobird.android.myapplication.dialog.FullscreenDialogFragment
import ru.nobird.android.view.base.ui.extension.snackbar

class DialogsActivity :
    AppCompatActivity(),
    AlertDialogFragment.Callback,
    FullscreenDialogFragment.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogs)

        supportActionBar?.apply {
            title = getString(R.string.topic_dialogfragment)
            setDisplayHomeAsUpEnabled(true)
        }

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    override fun onMobileDataStateChanged(isMobileAllowed: Boolean) {
        root.snackbar(message = isMobileAllowed.toString())
    }

    override fun onReviewCreated(text: String, rate: Int) {
        root.snackbar(message = text)
    }
}