package com.flyinbed.fnetwork.error_exception

import android.content.Context
import android.util.Log
import com.flyinbed.fnetwork.utils.TsDialog
import com.flyinbed.fnetwork.utils.showToast
import retrofit2.adapter.rxjava.HttpException
import java.io.IOException
import java.net.ConnectException


/**
 * 作者：Administrator on 2017/8/29 17:32
 * 邮箱：zhanghuaiha@gmail.com
 * 异常提示管理类
 */

object ApiErrorHelper {
    var flag : Boolean = false
    var dt : Boolean = false
    var msg : String? = null

    /**
     * @boolean 是否Toast异常显示 true为显示 false为不现实
     * @msg 是否自定义异常信息 默认为 操作失败.数据异常
     */
    fun hint2show(boolean: Boolean,msg : String){
        flag = boolean
        this.msg = msg
    }

    /**
     * @boolean 是否Toast异常显示 true为显示 false为不现实
     */
    fun hint2show(boolean: Boolean){
        flag = boolean
    }

    /**
     * @boolean 异常提示类型
     * true为Toast false为dialog，默认为dialog
     */
    fun dialog2Toast(boolean: Boolean){
        dt = boolean
    }

    fun handleCommonError(context: Context,e: Throwable) {
        if (dt){
            when(e){
                is ConnectException -> context.showToast("没有网络，请检查你的网络")
                is HttpException -> context.showToast("网络异常请重试")
                is IOException ->  context.showToast("数据加载失败，请检查您的网络")
            //后台返回的message
                is ApiException -> {
                    context.showToast(e.message!!)
                    Log.e("ApiErrorHelper",e.message,e)
                }
                else ->   {
                    if (flag){
                        if (null == msg){
                            context.showToast("操作失败.数据异常")
                        }else{
                            context.showToast(msg!!)
                        }
                    }
                    Log.e("ApiErrorHelper",e.message,e)
                }
            }
        }else{
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
                    if (flag){
                        if (null == msg){
                            context.showToast("操作失败.数据异常")
                        }else{
                            context.showToast(msg!!)
                        }
                    }
                    Log.e("ApiErrorHelper",e.message,e)
                }
            }
        }

   }
}
