package com.flyinbed.fnetwork.error_exception;

import com.flyinbed.fnetwork.BaseResponseEntity;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 作者：Administrator on 2017/8/29 17:08
 * 邮箱：zhanghuaiha@gmail.com
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Gson mGson;
    private final TypeAdapter<T> adapter;

    public MyGsonResponseBodyConverter(Gson mGson, TypeAdapter<T> adapter) {
        this.mGson = mGson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        //BaseResponseEntity 替换成自己定义的类
        BaseResponseEntity result = mGson.fromJson(response, BaseResponseEntity.class);
//        返回值判断改为自己接口定义的参数
        if (result.code != 200) {
            value.close();
            //第一个参数code码第二个参数异常提示信息
            throw new ApiException(result.code, result.message);
        }

        MediaType mediaType = value.contentType();
        Charset charset = mediaType != null ? mediaType.charset(UTF_8) : UTF_8;
        ByteArrayInputStream bis = new ByteArrayInputStream(response.getBytes());
        InputStreamReader reader = new InputStreamReader(bis,charset);
        JsonReader jsonReader = mGson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
