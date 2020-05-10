package ru.asshands.softwire.navigationapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_second.*


class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondFragment", "debug_onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_second, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: SecondFragmentArgs by navArgs()
        val firstFragmentValue = args.firstFragmentValue
        arg_second_value.setText(firstFragmentValue.toString())


        send_value_to_first_fragment.setOnClickListener {
            val secondFragmentValue = arg_second_value.text.toString().toInt()
            // Passing data between Destinations (Available in Navigation 2.3.0-alpha02 and higher):
            this.findNavController().previousBackStackEntry?.savedStateHandle?.set(
                "KEY_SECOND_VALUE",
                secondFragmentValue
            )
            this.findNavController().navigate(R.id.action_secondFragment_to_firstFragment)

//            Type-safe way get data arguments:
//            val action =
//            SecondFragmentDirections.actionSecondFragmentToFirstFragment(secondFragmentValue)
//            findNavController().navigate(action)

//            Bundle way set data arguments:
//            val bundle = bundleOf("KEY_SECOND_VALUE" to secondFragmentValue)
//            this.findNavController().navigate(R.id.action_secondFragment_to_firstFragment, bundle)


        }

        second_plus.setOnClickListener {
            arg_second_value.setText(arg_second_value.text.toString().toInt().plus(1).toString())
        }

        second_minus.setOnClickListener {
            arg_second_value.setText(arg_second_value.text.toString().toInt().minus(1).toString())
        }
    }

}