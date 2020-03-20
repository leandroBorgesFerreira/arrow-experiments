package br.com.leandroferreira.experiments;

import arrow.integrations.retrofit.adapter.CallKindAdapterFactory;
import br.com.leandroferreira.experiments.core.ExperimentsSdk;
import br.com.leandroferreira.experiments.reactor.ReactorExtensionsKt;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainJava {

    public void run() {
        reactorTest();
    }

    private void reactorTest() {
        ReactorExtensionsKt.experimentWithReactor(getSdk(), "experiment")
                .subscribe(result -> {
                    //Do something with the results!
                }, exception -> {
                    //Exception, do something
                });

    }

    private ExperimentsSdk getSdk() {
        return new ExperimentsSdk(getRetrofit());
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl("http://localhost.com")
                .addConverterFactory(getMoshi())
                .addCallAdapterFactory(CallKindAdapterFactory.Companion.create())
                .client(provideHttpClient())
                .build();
    }

    private MoshiConverterFactory getMoshi() {
        return MoshiConverterFactory.create(new Moshi.Builder().build());
    }

    private OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder().build();
    }
}
