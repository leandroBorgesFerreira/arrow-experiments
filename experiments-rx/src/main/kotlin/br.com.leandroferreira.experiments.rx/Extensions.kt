package br.com.leandroferreira.experiments.rx

import arrow.fx.rx2.SingleK
import arrow.fx.rx2.extensions.singlek.async.async
import arrow.fx.rx2.fix
import br.com.leandroferreira.experiments.core.ExperimentsSdk
import br.com.leandroferreira.experiments.core.domain.Experiment
import io.reactivex.Single

fun ExperimentsSdk.experimentWithRx(experimentName: String): Single<out List<Experiment>> =
    this.getExperimentResult(SingleK.async(), experimentName).fix().single

