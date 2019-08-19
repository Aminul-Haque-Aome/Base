package com.fake_coder.caretutor.utils

import android.content.Context
import android.net.ConnectivityManager
import java.lang.Boolean.valueOf

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object NetworkUtils {

    fun hasNetwork(context: Context): Boolean? {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnected
    }

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val headersInterceptor = Interceptor {
        val request = it.request()

        it.proceed(
            request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()
        )
    }

    fun getResponseCacheInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()

            if (valueOf(request.header("ApplyResponseCache"))) {
                it.proceed(request).newBuilder()
                    .removeHeader("ApplyResponseCache")
                    .header("Cache-Control", "public, max-age=" + 5)
                    .build()
            } else {
                it.proceed(request)
            }
        }
    }

    fun getOfflineResponseCacheInterceptor(context: Context): Interceptor {
        return Interceptor {
            val request = it.request()

            if (valueOf(request.header("ApplyResponseCache"))) {
                if (!hasNetwork(context)!!) {
                    it.proceed(request).newBuilder()
                        .removeHeader("ApplyResponseCache")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                        .build()
                } else {
                    it.proceed(request).newBuilder()
                        .removeHeader("ApplyResponseCache")
                        .header("Cache-Control", "public, max-age=" + 5)
                        .build()
                }
            } else {
                it.proceed(request)
            }
        }
    }
}