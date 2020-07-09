package ru.nobird.android.myapplication.fragment.arguments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.nobird.android.myapplication.R
import ru.nobird.android.view.base.ui.extension.argument

class ArgumentsFragment : Fragment() {
    companion object {
        private const val PERSON_ARG = "person"

//        fun newInstance(person: Person): Fragment {
//            val bundle = Bundle().apply {
//                putParcelable(PERSON_ARG, person)
//            }
//            return ArgumentsFragment().apply { arguments = bundle }
//        }

        fun newInstance(person: Person): Fragment =
            ArgumentsFragment().apply {
                this.person = person
            }
    }

//    private lateinit var person: Person
    private var person: Person by argument()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        person = arguments?.getParcelable(PERSON_ARG) ?: throw IllegalArgumentException()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_arguments, container, false)
}