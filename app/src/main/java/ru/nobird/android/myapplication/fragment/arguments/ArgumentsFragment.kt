package ru.nobird.android.myapplication.fragment.arguments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_arguments.*
import ru.nobird.android.myapplication.R
import ru.nobird.android.view.base.ui.extension.argument

class ArgumentsFragment : Fragment() {
    companion object {
        const val TAG = "ArgumentsFragment"
        private const val PERSON_ARG = "person"

        fun newInstance(person: Person): Fragment {
            val bundle = Bundle().apply {
                putParcelable(PERSON_ARG, person)
            }
            return ArgumentsFragment().apply { arguments = bundle }
        }

//   Good way
//        fun newInstance(person: Person): Fragment =
//            ArgumentsFragment().apply {
//                this.person = person
//            }
    }

    private lateinit var person: Person

//    Good way
//    private var person: Person by argument()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        person = arguments?.getParcelable(PERSON_ARG) ?: throw IllegalArgumentException()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_arguments, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        information.text = resources.getString(R.string.name_and_age, "${person.firstName} ${person.lastName}", person.age )
    }
}