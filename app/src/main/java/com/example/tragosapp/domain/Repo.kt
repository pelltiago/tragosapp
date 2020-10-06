package com.example.tragosapp.domain

import com.example.tragosapp.Drink
import com.example.tragosapp.vo.Resource

interface Repo {
   suspend fun getTragosList(tragoName:String): Resource<List<Drink>>
}