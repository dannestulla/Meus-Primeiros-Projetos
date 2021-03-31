package com.example.navcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation


class BlankFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    var buttonf1: Button? = null
    var buttonf2: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view) //pega a referencia pro navController pela view, que é a inflada no layout atual
        view.findViewById<Button>(R.id.buttonfrag2)?.setOnClickListener (this)
        view.findViewById<Button>(R.id.buttonfrag3)?.setOnClickListener (this)

    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.buttonfrag2 -> navController.navigate(R.id.action_blankFragment_to_blankFragment2)
            R.id.buttonfrag3 -> navController.navigate(R.id.action_blankFragment_to_blankFragment3)
        }
    }

}

