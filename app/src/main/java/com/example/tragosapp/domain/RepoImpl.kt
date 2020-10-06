package com.example.tragosapp.domain

import com.example.tragosapp.DataSource
import com.example.tragosapp.Drink
import com.example.tragosapp.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo{
    override suspend fun getTragosList(tragoName:String): Resource<List<Drink>> {
        return dataSource.getTragoByName(tragoName)
    }

}