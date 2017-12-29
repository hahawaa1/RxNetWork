package com.flyinbed.fnetwork

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.flyinbed.fnetwork.error_exception.MyGsonConverterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
 * 作者：Administrator on 2017/8/28 10:40
 * 邮箱：zhanghuaiha@gmail.com
 */
class RetrofitClient<T> private constructor(context: Context, baseUrl:String, clazz: Class<T>, var flag: Boolean){
    var httpCacheDirectory : File? = null
    val mContext : Context = context
    var cache : Cache? = null
    var okHttpClient : OkHttpClient? = null
    var retrofit : Retrofit? = null
    val DEFAULT_TIMEOUT : Long = 40
    val url = baseUrl
    val clazs = clazz
    var mApi : T? = null

    init {
        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
            Log.e("OKHttp", "Could not create http cache", e)
        }

        mApi = provideHotApi()

    }
    fun provideHotApi(): T?{
        //okhttp创建了
        okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(CacheInterceptor(mContext))
                .addNetworkInterceptor(CacheInterceptor(mContext))
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        //retrofit创建了
        retrofit = if (flag){
            Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(url)
                    .build()
        }else{
            Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(MyGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(url)
                    .build()
        }

        return retrofit?.create(clazs)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: RetrofitClient<*>? = null
        fun <T> getInstance(context: Context,baseUrl: String,clazz: Class<T>,flag : Boolean) : RetrofitClient<T> {
            if (instance == null) {
                synchronized(RetrofitClient ::class){
                    if (instance == null) {
                        instance = RetrofitClient(context,baseUrl,clazz,flag)
                    }
                }
            }
            return (instance as RetrofitClient<T>?)!!
        }
    }

}