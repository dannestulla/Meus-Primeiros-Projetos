package com.example.navcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation


class BlankFragment3 : Fragment(), View.OnClickListener  {
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.buttonfrag3voltar)?.setOnClickListener ( this )
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonfrag3voltar -> navController.navigate(R.id.action_blankFragment3_to_blankFragment)

        }
    }
}

