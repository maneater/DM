package com.maneater.dm.app.net;

import com.maneater.dm.app.model.Hospital;
import retrofit2.http.Url;
import rx.Observable;

import java.util.List;

public interface WebApi {
    Observable<Result<List<Hospital>>> listHospital(@Url String url);
}
