package com.flyinbed.fnetwork.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.text.DecimalFormat
import java.util.regex.Pattern


/**
 * Created by lvruheng on 2017/7/2.
 */
//强大的Toast
fun Context.showToast(message: String): Toast {
    var toast: Toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
//    toast.setGravity(Gravity.CENTER,0,0)
    toast.show()
    return toast
}



fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).
            unsubscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
}


fun Context.onUnsubscribe(){

}







