package com.example.minhasreceitas.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.minhasreceitas.R
import com.example.minhasreceitas.data.network.Meal
import com.example.minhasreceitas.databinding.FragmentInstructionsBinding
import com.example.minhasreceitas.viewmodel.InstructionsViewModel
import com.example.minhasreceitas.viewmodel.RecipesListViewModel.Companion.currentMeal
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class InstructionsFragment : Fragment() {
    private val viewModel by activityViewModels<InstructionsViewModel>()
    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progress.isVisible = true
        viewModel.isLoaded.postValue(false)
        viewModel.getInstructions(currentMeal[0].idMeal)
        viewModel.checkIfFavourited()



        //DISPLAY ITEMS, VISIBILITY ON/OFF
        viewModel.isLoaded.observe(viewLifecycleOwner, {
            if (it == true) {
                binding.apply {
                    addtofavourites.isVisible = true
                    progress.isVisible = false
                    textView27.isVisible = true
                    textView26.isVisible = true
                    textView25.isVisible = true
                    textView22.isVisible = true
                    textView28.isVisible = true
                    recipeimage.isVisible = true
                    textView17.isVisible = true
                }
            }
        })
        viewModel.descriptionData.apply {
            observe(viewLifecycleOwner, {
                binding.textView27.text = it[0].strInstructions
                binding.textView25.text = it[0].strArea
                binding.textView22.text = it[0].strMeal
                Picasso.get().load(it[0].strMealThumb).into(binding.recipeimage)
                //Create item to be favourited
                viewModel.favouriteItem = Meal(
                    it[0].idMeal,
                    it[0].strMeal,
                    it[0].strMealThumb,
                    it[0].strInstructions,
                    it[0].strArea,
                    it[0].favourite
                )
            })
        }

        //FAVOURITE BUTTON ON/OFF
        binding.addtofavourites.setOnClickListener {
            val buttonOn = viewModel.favButtonFromInstructions
            if (!buttonOn) {
                CoroutineScope(IO).launch { viewModel.addToItemToFavourites() }
                it.setBackgroundResource(R.drawable.favoriteon)
            } else {
                CoroutineScope(IO).launch { viewModel.removeItemFromFavourites() }
                it.setBackgroundResource(R.drawable.favoriteoff)
            }
        }
        //CHECK IF IS IN FAVOURITES
        viewModel.isFavourited.observe(viewLifecycleOwner, {
            if (it == true) {binding.addtofavourites.setBackgroundResource(R.drawable.favoriteon)}
            else {binding.addtofavourites.setBackgroundResource(R.drawable.favoriteoff)}
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.isLoaded.postValue(false)
        currentMeal.clear()
        Log.d("Instructions", "Destroyed")
        _binding = null
    }
}







