package com.maneater.dm.app.net;

import com.maneater.dm.app.model.Hospital;
import com.maneater.dm.app.model.Page;
import retrofit2.http.*;
import rx.Observable;

import java.util.List;

public interface WebApi {
    @GET("/hospital/list/{page}")
    @Headers("needLogin:true")
    Observable<Result<List<Hospital>>> listHospital(@Part("page") String page);

    @GET("/hospital/list")
    List<Hospital> listHospital(@Body Page page);

}
