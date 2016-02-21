package com.maneater.app.sport.net;

import com.maneater.app.sport.account.XAccount;
import com.maneater.app.sport.net.vo.LoginVo;
import com.maneater.app.sport.net.vo.PhoneVerCodeVo;
import com.maneater.app.sport.net.vo.RegisterVo;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebApi {

//    @POST("moble/phoneVerCode")
//    Observable<Result<Void>> phoneVerCode(@Body PhoneVerCodeVo phoneVerCodeVo);

    String Header_Need_Login = "need_login";
    String Header_Decode_Sid = "decode_sid";

    @POST("moble/phoneVerCode")
    Result<Void> phoneVerCode(@Body PhoneVerCodeVo phoneVerCodeVo);

    @POST("moble/user")
    Result<XAccount> register(@Body RegisterVo registerVo);

    @POST("moble/login")
    Result<XAccount> login(@Body LoginVo registerVo);

}
