package com.maneater.dm.app.net;

import retrofit2.http.Url;
import rx.Observable;

public interface WebApi {
    Observable<Result<String>> content(@Url String url);
}
