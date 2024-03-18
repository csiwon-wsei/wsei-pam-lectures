package pl.wsei.pam.lectures.lecture4.fragments.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pl.wsei.pam.lectures.R

class Lab04FragmentDetails : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab04_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.l4StartFragmentBtn)
        button.setOnClickListener(){
            val nav = findNavController()
            nav.navigate("start")
        }
        val city = arguments?.getString("city")
        Snackbar.make(button, "Selected city ${city}", Snackbar.LENGTH_SHORT).show()
    }
}