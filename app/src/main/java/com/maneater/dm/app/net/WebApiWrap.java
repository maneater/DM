package com.maneater.dm.app.net;

import android.os.SystemClock;
import com.maneater.dm.app.model.Hospital;
import retrofit2.http.Url;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class WebApiWrap implements WebApi {

    private WebApi webApi = null;

    private boolean debug = true;

    public WebApiWrap(WebApi webApi) {
        this.webApi = webApi;
    }

    @Override
    public Observable<Result<List<Hospital>>> listHospital(@Url String url) {
        if (debug) {
            Observable<Result<List<Hospital>>> myObservable = Observable.create(
                    new Observable.OnSubscribe<Result<List<Hospital>>>() {
                        @Override
                        public void call(Subscriber<? super Result<List<Hospital>>> subscriber) {
                            List<Hospital> hospitalList = new ArrayList<Hospital>();
                            SystemClock.sleep(3000);
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());
                            hospitalList.add(Hospital.demo());


                            subscriber.onNext(new Result<List<Hospital>>(hospitalList));
                            subscriber.onCompleted();
                        }
                    }
            );
            return myObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
        return webApi.listHospital(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
