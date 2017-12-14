package com.flyinbed.fnetwork.error_exception

import android.content.Context
import android.util.Log
import com.flyinbed.fnetwork.utils.TsDialog
import retrofit2.adapter.rxjava.HttpException
import java.io.IOException
import java.net.ConnectException


/**
 * 作者：Administrator on 2017/8/29 17:32
 * 邮箱：zhanghuaiha@gmail.com
 */

object ApiErrorHelper {
    fun handleCommonError(context: Context,e: Throwable) {

        when(e){
            is ConnectException -> context.TsDialog("没有网络，请检查你的网络",false)
            is HttpException -> context.TsDialog("网络异常请重试",false)
            is IOException ->  context.TsDialog("数据加载失败，请检查您的网络",false)
            //后台返回的message
            is ApiException -> {
                context.TsDialog(e.message!!,false)
                Log.e("ApiErrorHelper",e.message,e)
            }
            else ->   {
                context.TsDialog(e.message!!,false)
                Log.e("ApiErrorHelper",e.message,e)
            }
        }
   }
}
