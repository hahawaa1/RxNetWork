package com.flyinbed.rxnetwork

import com.flyinbed.fnetwork.BaseResponseEntity
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

/**
 * Created by flyinbed on 17/12/11.
 *
 */
interface ApiService {
    //http://api.douban.com/v2/movie/top250
    //当RetrofitClient初始化的时候第四个参数为true的时候BaseResponseEntity自定义
    companion object{
        val BASE_URL : String
            get() = "http://api.douban.com"
    }

    @GET("/v2/movie/top250")
    fun getTop(): Observable<BaseResponseEntity<List<Top>>>

}