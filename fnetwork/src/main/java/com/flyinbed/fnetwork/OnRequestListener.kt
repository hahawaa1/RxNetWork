package com.flyinbed.fnetwork

/**
 * 作者：Administrator on 2017/8/28 16:25
 * 邮箱：zhanghuaiha@gmail.com
 */
interface OnRequestListener<in T> {
    fun success(t : T)
}