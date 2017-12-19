package com.flyinbed.rxnetwork

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.flyinbed.fnetwork.RetrofitClient
import com.flyinbed.fnetwork.error_exception.ApiErrorHelper


/**
 * 作者：Administrator on 2017/9/7 15:15
 * 邮箱：zhanghuaiha@gmail.com
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = this
        //初始化通用的SP&EDIT
        SP = getSharedPreferences("config", Context.MODE_PRIVATE)
        EDIT = SP?.edit()
        instance = RetrofitClient.getInstance(this, ApiService.BASE_URL, ApiService::class.java,true).mApi
        ApiErrorHelper.hint2show(true)
    }

    companion object {
        var mContext: Context? = null
        /**
         * 初始化SP&EDIT
         */
        var SP: SharedPreferences? = null
        var EDIT: SharedPreferences.Editor? = null
        var instance : ApiService? = null
    }
}