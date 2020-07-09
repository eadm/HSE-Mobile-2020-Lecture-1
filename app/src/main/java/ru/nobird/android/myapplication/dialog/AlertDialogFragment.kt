package ru.nobird.android.myapplication.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.nobird.android.myapplication.R
import ru.nobird.android.myapplication.extension.getTarget

class AlertDialogFragment : DialogFragment() {
    companion object {
        const val TAG = "AlertDialogFragment"

        fun newInstance(): DialogFragment =
            AlertDialogFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.allow_mobile_download_title)
            .setMessage(R.string.allow_mobile_message)
            .setPositiveButton(R.string.yes) { _, _ ->
                getTarget<Callback>()
                    ?.onMobileDataStateChanged(true)
            }
            .setNegativeButton(R.string.no) { _, _ ->
                getTarget<Callback>()
                    ?.onMobileDataStateChanged(false)
            }
            .create()

    /**
     * Интерфейс, который должен быть реализован родителем
     */
    interface Callback {
        fun onMobileDataStateChanged(isMobileAllowed: Boolean)
    }
}