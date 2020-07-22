package ru.nobird.android.myapplication.arch.view.movie.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.nobird.android.myapplication.R
import ru.nobird.android.myapplication.extension.getTarget

class CreateMovieDialogFragment : DialogFragment() {
    companion object {
        const val TAG = "CreateMovieDeialogFragment"

        fun newInstance(): DialogFragment =
            CreateMovieDialogFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_create_movie, null, false)

        val editText = view.findViewById<EditText>(R.id.edit_text)

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.movie_create)
            .setView(view)
            .setPositiveButton(R.string.movie_create_short) { _, _ ->
                getTarget<Callback>()
                    ?.onCreateMovie(editText.text.toString())
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()
    }


    /**
     * Интерфейс, который должен быть реализован родителем
     */
    interface Callback {
        fun onCreateMovie(name: String)
    }
}