package com.maneater.dm.app.net;

import android.util.Log;
import com.maneater.dm.app.net.core.GsonConverterFactory;
import okhttp3.*;
import retrofit2.Converter;
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
//                .addConverterFactory(JObjectConvertFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return new WebApiWrap(retrofit.create(WebApi.class));
    }

    private static class NetWorkInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            okhttp3.Request request = chain.request();

            if ("true".equals(request.header("needLogin"))) {
                //额外统一添加header
                request = request.newBuilder().addHeader("token", "asdfadfasdf").build();
            }

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
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return super.requestBodyConverter(type, annotations, retrofit);
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type == boolean.class || type == Boolean.class ||
                    type == int.class || type == Integer.class ||
                    type == String.class ||
                    type == Result.class) {
                return new Converter<ResponseBody, Object>() {
                    @Override
                    public Object convert(ResponseBody value) throws IOException {
                        if (type == boolean.class || type == Boolean.class) {
                            return Boolean.parseBoolean(value.string());
                        } else if (type == int.class || type == Integer.class) {
                            return Integer.parseInt(value.string());
                        } else if (type == String.class) {
                            return value.string();
                        } else if (type == Result.class) {
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
