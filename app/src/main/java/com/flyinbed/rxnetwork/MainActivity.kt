package com.flyinbed.rxnetwork

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.flyinbed.fnetwork.BaseResponseEntity
import com.flyinbed.fnetwork.HttpObserver
import com.flyinbed.fnetwork.utils.applySchedulers
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import rx.subscriptions.CompositeSubscription

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecycler.layoutManager = LinearLayoutManager(this)
        network()

    }


    private var mCompositeSubscription : CompositeSubscription? = null

    fun network(){
        mCompositeSubscription = CompositeSubscription()
        //HttpObserver<BaseResponseEntity<List<Top>>>(this,true)当为true的时候显示加载dialog，false则不显示
        mCompositeSubscription?.add(App.instance?.getTop()?.applySchedulers()?.subscribe(object : HttpObserver<BaseResponseEntity<List<Top>>>(this,true){

            override fun success(t:BaseResponseEntity<List<Top>>) {

                mRecycler.adapter = (object : CommonAdapter<Top>(this@MainActivity, R.layout.item_list,t.subjects) {
                    override fun convert(holder: ViewHolder?, t: Top?, position: Int) {
                        holder?.setText(R.id.name,t?.title)
                        holder?.setText(R.id.year,t?.year)
                    }
                })
            }
        }))
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mCompositeSubscription!!.hasSubscriptions()) {
            mCompositeSubscription?.unsubscribe()
        }
    }
}
