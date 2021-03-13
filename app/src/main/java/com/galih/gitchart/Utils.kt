package com.galih.gitchart

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

// currently not using this function
fun customGlider(activity: Activity, resource: String?, size: Int, target: ImageView) {
    val resName = resource?.subSequence(1, resource.length)
    Glide.with(activity)
        .load(resName)
        .apply(RequestOptions().override(size))
        .into(target)
}

// currently not using this function
fun customGlider(context: Context, resource: String?, size: Int, target: ImageView) {
    val resName = resource?.subSequence(1, resource.length)
    Glide.with(context)
        .load(resName)
        .apply(RequestOptions().override(size))
        .into(target)
}