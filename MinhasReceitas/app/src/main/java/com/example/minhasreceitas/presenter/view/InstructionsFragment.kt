package com.example.minhasreceitas.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.minhasreceitas.R
import com.example.minhasreceitas.data.remote.Meal
import com.example.minhasreceitas.databinding.FragmentInstructionsBinding
import com.example.minhasreceitas.presenter.viewmodel.InstructionsViewModel
import com.example.minhasreceitas.presenter.viewmodel.InstructionsViewModel.Companion.currentMeal
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
        viewModel.getInstructions(currentMeal.idMeal)
        viewModel.checkIfFavourited()
        viewModel.isLoaded.observe(viewLifecycleOwner, {
            if (it == true) {
                displayItemsOnScreen()
            }
        }
        )
        viewModel.descriptionData.apply {
            observe(viewLifecycleOwner, {
                binding.instructionsfulltext.text = it[0].strInstructions
                binding.cuisinetype.text = it[0].strArea
                binding.recipetitle.text = it[0].strMeal
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
                Toast.makeText(context, "Added To Favourites!", Toast.LENGTH_LONG).show()
            } else {
                CoroutineScope(IO).launch { viewModel.removeItemFromFavourites() }
                it.setBackgroundResource(R.drawable.favoriteoff)
                Toast.makeText(context, "Removed from Favourites!", Toast.LENGTH_LONG).show()
            }
        }

        binding.instructionsbackbutton.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_recipeFragment)
        }

        //CHECK IF IS IN FAVOURITES
        viewModel.isFavourited.observe(viewLifecycleOwner, {
            if (it == true) {
                binding.addtofavourites.setBackgroundResource(R.drawable.favoriteon)
            } else {
                binding.addtofavourites.setBackgroundResource(R.drawable.favoriteoff)
            }
        })
    }

    private fun displayItemsOnScreen() {
        binding.apply {
            addtofavourites.isVisible = true
            progress.isVisible = false
            recipetitle.isVisible = true
            cuisinetype.isVisible = true
            instructionstext.isVisible = true
            instructionsfulltext.isVisible = true
            addtofavourites.isVisible = true
            recipeimage.isVisible = true
            textfavourite.isVisible = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.isLoaded.postValue(false)
        currentMeal = Meal("", "", "", "", "", "")
        _binding = null
    }
}







