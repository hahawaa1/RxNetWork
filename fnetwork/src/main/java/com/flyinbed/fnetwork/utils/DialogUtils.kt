package com.flyinbed.fnetwork.utils

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.flyinbed.fnetwork.R

/**
 * Created by flyinbed on 17/12/11.
 * dialog工具类
 */

//加载等待dialog
fun Context.loadDialog(msg:String,flag:Boolean) : Dialog{
    val progressDialog = Dialog(this, R.style.progress_dialog)
    progressDialog.setContentView(R.layout.dialog_loading)
    progressDialog.setCancelable(true)
    progressDialog.setCanceledOnTouchOutside(flag)
    progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    val tv_msg = progressDialog.findViewById<TextView>(R.id.id_tv_loadingmsg)
    tv_msg.text = msg
    return progressDialog
}

//提示dialog
fun Context.TsDialog(msg:String,flag:Boolean){
    val progressDialog = Dialog(this, R.style.progress_dialog)
    progressDialog.setContentView(R.layout.dialog_ts)
    progressDialog.setCancelable(true)
    progressDialog.setCanceledOnTouchOutside(flag)
    progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    val tv_msg = progressDialog.findViewById<TextView>(R.id.id_tv_loadingmsg)
    val save = progressDialog.findViewById<TextView>(R.id.dialog_save)
    save.setOnClickListener {
        progressDialog.dismiss()
    }
    tv_msg.text = msg
    progressDialog.show()
}
