package com.maneater.app.sport.net;

import android.util.Log;
import com.maneater.app.sport.account.XAccount;
import com.maneater.app.sport.account.XAccountManager;
import com.maneater.app.sport.net.core.GsonConverterFactory;
import com.maneater.app.sport.net.vo.LoginVo;
import com.maneater.app.sport.net.vo.PhoneVerCodeVo;
import com.maneater.app.sport.net.vo.RegisterVo;
import com.maneater.base.util.StringUtils;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.Body;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class WebApiFactory {

    private final static String BASE_URL = "http://101.201.210.249:18080/";

    public static String Cache_Sid = "";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new NetWorkInterceptor()).build();

    public static WebApi createApi() {
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(SimpleCallAdapterFactory.create())
                .build();
        return new WebApiWrap(retrofit.create(WebApi.class));
    }

    private static class SimpleCallAdapterFactory implements CallAdapter.Factory {
        public static CallAdapter.Factory create() {
            return new SimpleCallAdapterFactory();
        }

        static final class ResponseCallAdapter implements CallAdapter<Result<?>> {
            private final Type responseType;

            ResponseCallAdapter(Type responseType) {
                this.responseType = responseType;
            }

            @Override
            public Type responseType() {
                return responseType;
            }

            @Override
            public <R> Result<?> adapt(Call<R> call) {
                try {
                    Result<?> result = (Result<?>) call.execute().body();
                    if (result == null) {
                        result = Error_Result_No_Response;
                    }
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return Error_Result_Net;
            }
        }

        @Override
        public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
            return new ResponseCallAdapter(returnType);
        }
    }


    private static class WebApiWrap implements WebApi {

        private WebApi webApi = null;

        public WebApiWrap(WebApi webApi) {
            this.webApi = webApi;
        }


        @Override
        public Result<Void> phoneVerCode(@Body PhoneVerCodeVo phoneVerCodeVo) {
            return webApi.phoneVerCode(phoneVerCodeVo);
        }

        @Override
        public Result<XAccount> register(@Body RegisterVo registerVo) {
            return webApi.register(registerVo);
        }

        @Override
        public Result<XAccount> login(@Body LoginVo registerVo) {
            return webApi.login(registerVo);
        }
    }

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static Result<Void> Error_Result_Net = new Result<Void>("网络请求出错,请稍后再试验", -1);
    private static String Error_Result_Net_Json = Error_Result_Net.toJson();
    private static Result<Void> Error_Result_Other = new Result<Void>("内部错误", -2);
    private static Result<Void> Error_Result_No_Response = new Result<Void>("内部错误:没有返回数据", -2);
    private static String Error_Result_Other_Json = Error_Result_Other.toJson();

    private static class NetWorkInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            //额外统一添加header
            if (StringUtils.isNotBlank(request.header(WebApi.Header_Need_Login))) {
                request = request.newBuilder().addHeader("sid", XAccountManager.getInstance().peekSession()).build();
            }

            long t1 = System.nanoTime();
            Log.i("WebApi", String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

            Response response;
            try {
                boolean decodeSid = StringUtils.isNotBlank(request.header(WebApi.Header_Decode_Sid));
                if (decodeSid) {
                    Cache_Sid = null;
                }
                response = chain.proceed(request);

                if (decodeSid) {
                    Cache_Sid = response.header("sid");
                }

                long t2 = System.nanoTime();
                Log.i("WebApi", String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
                Log.i("WebApi", String.format("Status code:", response.code()));

            } catch (Exception e) {
                e.toString();
                response = new Response.Builder().code(200).body(ResponseBody.create(MEDIA_TYPE, Error_Result_Other_Json)).request(request).build();
            }

            return response;
        }
    }

//    private static class JObjectConvertFactory extends Converter.Factory {
//        private JObjectConvertFactory() {
//            super();
//        }
//
//        @Override
//        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//            return super.requestBodyConverter(type, annotations, retrofit);
//        }
//
//        @Override
//        public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
//            if (type == boolean.class || type == Boolean.class ||
//                    type == int.class || type == Integer.class ||
//                    type == String.class ||
//                    type == Result.class) {
//                return new Converter<ResponseBody, Object>() {
//                    @Override
//                    public Object convert(ResponseBody value) throws IOException {
//                        if (type == boolean.class || type == Boolean.class) {
//                            return Boolean.parseBoolean(value.string());
//                        } else if (type == int.class || type == Integer.class) {
//                            return Integer.parseInt(value.string());
//                        } else if (type == String.class) {
//                            return value.string();
//                        } else if (type == Result.class) {
//                            return value.string();
//                        }
//                        return null;
//                    }
//                };
//            }
//            return null;
//        }
//
//        public static Converter.Factory create() {
//            return new JObjectConvertFactory();
//        }
//    }
}
