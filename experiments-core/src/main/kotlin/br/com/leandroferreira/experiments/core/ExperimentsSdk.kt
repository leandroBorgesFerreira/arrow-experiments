package br.com.leandroferreira.experiments.core

import arrow.Kind
import arrow.fx.extensions.io.concurrent.dispatchers
import arrow.fx.typeclasses.Async
import arrow.integrations.retrofit.adapter.CallKindAdapterFactory
import arrow.typeclasses.Functor
import br.com.leandroferreira.experiments.core.domain.Experiment
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ExperimentsSdk(private val retrofit: Retrofit = getRetrofit()) {

    private fun getApi() = retrofit.create(ExperimentsApi::class.java)

    fun <F> getExperimentResult(async: Async<F>, experiment: String): Kind<F, List<Experiment>> =
        async.run(getValue(async, experiment, getApi())::parseResponse)
}

private fun getRetrofit(): Retrofit =
    Retrofit.Builder().baseUrl("http://localhost.com")
        .addConverterFactory(getMoshi())
        .addCallAdapterFactory(CallKindAdapterFactory.create())
        .client(provideHttpClient())
        .build()

private fun getMoshi() = MoshiConverterFactory.create(
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
)

private fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
