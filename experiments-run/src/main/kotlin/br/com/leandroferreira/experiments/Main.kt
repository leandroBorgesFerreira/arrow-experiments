package br.com.leandroferreira.experiments

import arrow.integrations.retrofit.adapter.CallKindAdapterFactory
import br.com.leandroferreira.experiments.core.ExperimentsSdk
import br.com.leandroferreira.experiments.core.domain.Experiment
import br.com.leandroferreira.experiments.reactor.experimentWithReactor
import br.com.leandroferreira.experiments.rx.experimentWithRx
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun main() {
    rxTest()
    reactorTest()
}

private fun rxTest() {
    ExperimentsSdk(getRetrofit())
        .experimentWithRx("experiment")
        .subscribe(::handleSuccess, ::handleFailure)
}

private fun reactorTest() {
    ExperimentsSdk(getRetrofit())
        .experimentWithReactor("experiment")
        .subscribe(::handleSuccess, ::handleFailure)
}

private fun handleSuccess(result: List<Experiment>) {
    println("Test result. Fold: ${foldExperiments(result)}")
}

private fun foldExperiments(experiments: List<Experiment>): String =
    experiments.foldRight("", { exp, acc -> "${exp.id} $acc" })

private fun handleFailure(throwable: Throwable) {
    println("Something when wrong o.O: ${throwable.message}")
}

private fun getRetrofit(): Retrofit =
    Retrofit.Builder().baseUrl("http://localhost.com")
        .addConverterFactory(getMoshi())
        .addCallAdapterFactory(CallKindAdapterFactory.create())
        .client(provideHttpClient())
        .build()

private fun getMoshi(): MoshiConverterFactory = MoshiConverterFactory.create(
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
)

private fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
