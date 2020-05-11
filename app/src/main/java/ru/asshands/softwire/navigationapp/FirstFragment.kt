package ru.asshands.softwire.navigationapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FirstFragment", "debug_onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_first, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Passing data between Destinations (Available in Navigation 2.3.0-alpha02 and higher)
        val navController = findNavController()
        navController
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Int>("KEY_SECOND_VALUE")
            ?.observe(viewLifecycleOwner, Observer {
                arg_second_value.text = it.toString()
//                navController
//                    .currentBackStackEntry
//                    ?.savedStateHandle?.remove<Int>("KEY_SECOND_VALUE")
            })


        /**
         * If you use type-safe way
         * Don't forget apply plugin: 'androidx.navigation.safeargs'
         * and set Compatibility JavaVersion.VERSION_1_8
         */

//      Type-safe way get data arguments:
//      val args: FirstFragmentArgs by navArgs()
//      val secondFragmentValue = args.secondFragmentValue
//      arg_second_value.text = secondFragmentValue.toString()


//      Bundle way get data arguments:
//      val secondFragmentValue = arguments?.getInt("KEY_SECOND_VALUE", 666)
//      arg_second_value.text = secondFragmentValue.toString()

        first_send_this_value.setOnClickListener {
            val firstFragmentValue = first_value.text.toString().toInt()
            val action =
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(firstFragmentValue)
            findNavController().navigate(action)
        }

        first_plus.setOnClickListener {
            first_value.setText(first_value.text.toString().toInt().plus(1).toString())
        }

        first_minus.setOnClickListener {
            first_value.setText(first_value.text.toString().toInt().minus(1).toString())
        }
    }
}