package ru.nobird.android.myapplication.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_fullscreen.*
import ru.nobird.android.myapplication.R
import ru.nobird.android.myapplication.extension.getTarget

class FullscreenDialogFragment : DialogFragment() {
    companion object {
        const val TAG = "FullscreenDialogFragment"

        fun newInstance(): DialogFragment =
            FullscreenDialogFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ThemeOverlay_AppTheme_Dialog_Fullscreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_fullscreen, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationIcon(R.drawable.ic_close)
        toolbar.setNavigationOnClickListener { dismiss() }
        toolbar.inflateMenu(R.menu.menu_fullscreen_dialog)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.review_submit -> {
                    getTarget<Callback>()
                        ?.onReviewCreated(reviewEditText.text.toString(), reviewRating.rating.toInt())
                    dismiss()
                    true
                }

                else ->
                    false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog
            ?.window
            ?.let { window ->
                window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                window.setWindowAnimations(R.style.ThemeOverlay_AppTheme_Dialog_Fullscreen)
            }
    }

    interface Callback {
        fun onReviewCreated(text: String, rate: Int)
    }
}