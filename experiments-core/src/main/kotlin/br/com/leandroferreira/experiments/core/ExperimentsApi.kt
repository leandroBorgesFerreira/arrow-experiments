package br.com.leandroferreira.experiments.core

import arrow.integrations.retrofit.adapter.CallK
import br.com.leandroferreira.experiments.core.domain.Experiment
import retrofit2.http.GET
import retrofit2.http.Path

interface ExperimentsApi {

    @GET("experiments/{id}")
    fun experiment(@Path("id") id: String): CallK<List<Experiment>>
}