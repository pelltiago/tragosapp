package com.example.tragosapp

import com.example.tragosapp.vo.Resource
import com.example.tragosapp.vo.RetrofitClient

class DataSource {
    suspend fun getTragoByName(tragoName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webService.getTragoByName(tragoName).drinkList)
    }



}