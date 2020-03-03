package br.com.leandroferreira.experiments.core

import arrow.Kind
import arrow.fx.typeclasses.Async
import br.com.leandroferreira.experiments.core.domain.Experiment
import retrofit2.Response

fun <F> getValue(async: Async<F>, experiment: String, api: ExperimentsApi): Kind<F, Response<List<Experiment>>> =
    api.experiment(experiment).async(async)