package br.com.leandroferreira.experiments.core.domain

import com.squareup.moshi.Json

data class Experiment(
    @Json(name = "id") val id: String,
    @Json(name = "value") val value: Boolean
)