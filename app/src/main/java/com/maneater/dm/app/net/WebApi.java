package com.maneater.dm.app.net;

import com.maneater.dm.app.model.Weather;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface WebApi {

    @GET
    Observable<Weather> demo1(@Url String url);

    @GET
    Observable<String> content(@Url String url);
}
