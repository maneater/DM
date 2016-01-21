/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.maneater.dm.app.net.core;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.maneater.base.util.InjectUtil;
import com.maneater.dm.app.net.Result;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private TypeAdapter adapter;
    private boolean isWrap = false;

    GsonResponseBodyConverter(Gson gson, Type type) {
        if (InjectUtil.getRawType(type) == Result.class) {
            Type parameterType = InjectUtil.getParameterUpperBound(0, (ParameterizedType) type);
            adapter = gson.getAdapter(TypeToken.get(parameterType));
            isWrap = true;
        } else {
            adapter = gson.getAdapter(TypeToken.get(type));
        }
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            if (isWrap) {
                Object data = adapter.fromJson(value.charStream());

            } else {
                return (T) adapter.fromJson(value.charStream());
            }
        } catch (Exception e) {

        } finally {
            value.close();
        }
        return null;
    }

}
