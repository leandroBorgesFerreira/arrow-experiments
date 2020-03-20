package br.com.leandroferreira.experiments.reactor

import arrow.fx.reactor.MonoK
import arrow.fx.reactor.extensions.monok.async.async
import arrow.fx.reactor.fix
import br.com.leandroferreira.experiments.core.ExperimentsSdk
import br.com.leandroferreira.experiments.core.domain.Experiment
import reactor.core.publisher.Mono

fun ExperimentsSdk.experimentWithReactor(experimentName: String): Mono<out List<Experiment>> =
    this.getExperimentResult(MonoK.async(), experimentName).fix().mono
