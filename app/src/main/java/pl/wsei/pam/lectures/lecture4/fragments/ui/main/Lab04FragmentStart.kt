package pl.wsei.pam.lectures.lecture4.fragments.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavAction
import androidx.navigation.NavArgument
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDestinationBuilder
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import pl.wsei.pam.lectures.R
import javax.crypto.MacSpi

class Lab04FragmentStart : Fragment() {
    private lateinit var mSpinner: Spinner
    private lateinit var mButton: Button
    companion object {
        fun newInstance() = Lab04FragmentStart()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_lab04_start, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        mSpinner = view.findViewById<Spinner>(R.id.l4CitiesSpinner)
        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.cities,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                mSpinner.adapter = adapter
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }
        mButton = view.findViewById(R.id.l4DetailsFragmentBtn)
        mButton.setOnClickListener(::startDetailsFragment)
    }

    fun startDetailsFragment(v: View){
        findNavController().navigate("details/${mSpinner.selectedItem}",
            )
    }
}