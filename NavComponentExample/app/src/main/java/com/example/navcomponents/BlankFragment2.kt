package com.example.navcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation


class BlankFragment2 : Fragment(), View.OnClickListener {
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view) //pega a referencia pro navController pela view, que é a inflada no layout atual
        view.findViewById<Button>(R.id.buttonFrag2Voltar)?.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonFrag2Voltar -> navController.navigate(R.id.action_blankFragment2_to_blankFragment)
        }
    }


}