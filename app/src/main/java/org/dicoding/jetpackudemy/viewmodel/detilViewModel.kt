package org.dicoding.jetpackudemy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.dicoding.jetpackudemy.model.DogBreed

class detilViewModel :ViewModel(){
    val dogs = MutableLiveData<DogBreed>()

    fun fetch(){
        val dog1 = DogBreed("1","lambo","23 years",
            "bereed","fdfd","tempreametn","")
        dogs.value = dog1
    }
}