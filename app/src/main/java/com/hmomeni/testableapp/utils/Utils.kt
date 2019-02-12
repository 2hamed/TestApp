package com.hmomeni.testableapp.utils


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.provider.Settings
import android.view.View
import com.hmomeni.testableapp.App
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun Application.app(): App {
    return this as App
}

fun Context.app(): App {
    return applicationContext as App
}

fun <T> schedulers(): FlowableTransformer<T, T> = FlowableTransformer {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.iomain(): Flowable<T> = this.compose(schedulers())
fun <T> Single<T>.iomain(): Single<T> = this.compose {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun Completable.iomain(): Completable = this.compose {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.iomain(): Maybe<T> = this.compose {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}


fun dpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()


@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): String =
    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}