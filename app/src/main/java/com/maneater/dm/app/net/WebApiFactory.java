package com.maneater.dm.app.net;

import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class WebApiFactory {

    private final static String BASE_URL = "https://api.github.com";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new NetWorkInterceptor()).build();

    public static WebApi createApi() {
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(JObjectConvertFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(WebApi.class);
    }

    private static class NetWorkInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            okhttp3.Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i("WebApi", String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i("WebApi", String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }

    private static class JObjectConvertFactory extends Converter.Factory {
        private JObjectConvertFactory() {
            super();
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type == boolean.class || type == Boolean.class ||
                    type == int.class || type == Integer.class ||
                    type == String.class) {
                return new Converter<ResponseBody, Object>() {
                    @Override
                    public Object convert(ResponseBody value) throws IOException {
                        if (type == boolean.class || type == Boolean.class) {
                            return Boolean.parseBoolean(value.string());
                        } else if (type == int.class || type == Integer.class) {
                            return Integer.parseInt(value.string());
                        } else if (type == String.class) {
                            return value.string();
                        }
                        return null;
                    }
                };
            }
            return null;
        }

        public static Converter.Factory create() {
            return new JObjectConvertFactory();
        }
    }
}
