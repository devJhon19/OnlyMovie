package com.dev.jhon.onlymovie.domain.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.marcoscg.materialtoast.MaterialToast

inline fun <reified T> T.logi(message: String) = Log.i(T::class.java.simpleName, message)
inline fun <reified T> T.logw(message: String) = Log.w(T::class.java.simpleName, message)
inline fun <reified T> T.logerror(message: String) = Log.e(T::class.java.simpleName, message)

fun Context.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    MaterialToast.makeText(this, text, length).show();
}
