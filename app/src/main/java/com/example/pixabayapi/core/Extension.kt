package com.example.pixabayapi.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.ImageView
import com.bumptech.glide.Glide

fun Context.isOnline(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
    return false
}

fun ImageView.setPictureDataFromHit(context: Context, imageUrl: String) {
    Glide
        .with(context)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}

fun ImageView.setPictureDataFromHitRounded(context: Context, imageUrl: String) {
    Glide
        .with(context)
        .load(imageUrl)
        .circleCrop()
        .into(this)
}