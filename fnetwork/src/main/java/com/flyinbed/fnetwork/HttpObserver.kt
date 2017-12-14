package com.flyinbed.fnetwork


import android.app.Dialog
import android.content.Context
import com.flyinbed.fnetwork.error_exception.ApiErrorHelper
import com.flyinbed.fnetwork.utils.loadDialog


/**
 * 作者：Administrator on 2017/8/28 16:22
 * 邮箱：zhanghuaiha@gmail.com
 */
abstract class HttpObserver<T>(context: Context,val flag : Boolean) : MySubscriber<T>(), OnRequestListener<T> {
    var loadDialog: Dialog? = null
    var contexts : Context?  = null

    init {
        if (flag){
            contexts = context
            loadDialog = context.loadDialog("加载中", false)
            loadDialog?.show()
        }

    }

    override fun onCompleted() {
        if (loadDialog != null){
            loadDialog?.dismiss()
        }
    }

    override fun onNext(t: T) {
        success(t)
    }

    override fun onError(t: Throwable?) {
        if (loadDialog != null){
            loadDialog?.dismiss()
        }
        ApiErrorHelper.handleCommonError(contexts!!,t!!)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun unsubscribe() {
        super.unsubscribe()
    }
}