package br.com.leandroferreira.experiments.core

import arrow.Kind
import arrow.fx.typeclasses.Async
import retrofit2.Response

fun <F, T> Kind<F, Response<T>>.parseResponse(async: Async<F>): Kind<F, T> =
    async.run {
        this@parseResponse.map { response ->
            if (response.isSuccessful) {
                response.body() ?: throw throw RuntimeException("Empty body")
            } else {
                throw RuntimeException("Error")
            }
        }
    }