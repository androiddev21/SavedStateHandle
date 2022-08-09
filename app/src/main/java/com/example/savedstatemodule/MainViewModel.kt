package com.example.savedstatemodule

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.savedstatemodule.model.Squares
import kotlin.random.Random

//view model default factory can handle this case
class MainViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    //if it`s not important to save first generated value
   // private val _squares = savedStateHandle.getLiveData(KEY_SQUARES, createSquares())
    private val _squares = savedStateHandle.getLiveData<Squares>(KEY_SQUARES)
    val squares: LiveData<Squares> = _squares

    //to save first generated value
    init {
        if (!savedStateHandle.contains(KEY_SQUARES)) {
            savedStateHandle[KEY_SQUARES] = createSquares()
        }
    }

    fun generateSquares() {
        _squares.value = createSquares()
    }

    private fun createSquares(): Squares {
        return Squares(
            size = Random.nextInt(5, 10),
            colorProducer = {
                -Random.nextInt(0xFFFFFF)
            }
        )
    }

    companion object{
        private const val KEY_SQUARES = "squares"
    }
}