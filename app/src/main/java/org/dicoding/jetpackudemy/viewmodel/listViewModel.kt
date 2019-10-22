package org.dicoding.jetpackudemy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.dicoding.jetpackudemy.model.DogApiServices
import org.dicoding.jetpackudemy.model.DogBreed

class listViewModel:ViewModel(){
    private val dogSevice = DogApiServices()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadEror = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogSevice.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<DogBreed>>(){
                    override fun onSuccess(doglist: List<DogBreed>) {
                        dogs.value = doglist
                        dogsLoadEror.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadEror.value = true
                        loading.value = false


                    }

                })

        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}