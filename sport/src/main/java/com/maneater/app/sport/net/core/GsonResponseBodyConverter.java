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
package com.maneater.app.sport.net.core;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final static String TAG = "Response";
    private TypeAdapter adapter;

    GsonResponseBodyConverter(Gson gson, Type type) {
        adapter = gson.getAdapter(TypeToken.get(type));
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String content = value.string();
            Log.d(TAG, content);
            return (T) adapter.fromJson(content);
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            value.close();
        }
    }

}
