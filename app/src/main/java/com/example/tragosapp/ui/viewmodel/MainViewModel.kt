package com.example.tragosapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.tragosapp.domain.Repo
import com.example.tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo:Repo): ViewModel() {

    private val tragosData = MutableLiveData<String>()

    fun setTrago(tragoName:String){
        tragosData.value = tragoName

    }

    init {
        setTrago("margarita")
    }

    val fetchTragosList = tragosData.distinctUntilChanged().switchMap {nombreTrago ->
        liveData(Dispatchers.IO){
            emit(Resource.Loading())
            try {
                emit(repo.getTragosList(nombreTrago))
            }catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }
    }
}