package org.dicoding.jetpackudemy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.dicoding.jetpackudemy.model.DogBreed

class listViewModel:ViewModel(){
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadEror = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val dog1 = DogBreed("1","lambo","23 years",
            "bereed","fdfd","tempreametn","")
        val dog2 = DogBreed("2","labrador","23 years",
            "bereed","fdfd","tempreametn","")
        val dog3 = DogBreed("3","mini","23 years",
            "bereed","fdfd","tempreametn","")

        val doglist = arrayListOf<DogBreed>(dog1,dog2,dog3)

        dogs.value = doglist
        dogsLoadEror.value = false
        loading.value = false
    }
}