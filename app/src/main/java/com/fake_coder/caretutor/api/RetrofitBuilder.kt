package com.fake_coder.caretutor.api

import android.content.Context
import java.util.concurrent.TimeUnit

import okhttp3.Cache
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import com.fake_coder.caretutor.utils.NetworkUtils.getOfflineResponseCacheInterceptor
import com.fake_coder.caretutor.utils.NetworkUtils.getResponseCacheInterceptor
import com.fake_coder.caretutor.utils.NetworkUtils.headersInterceptor
import com.fake_coder.caretutor.utils.NetworkUtils.loggingInterceptor

object RetrofitBuilder {

    fun retrofit(baseUrl: String, context: Context): Retrofit = Retrofit.Builder()
        .client(getOkHttpClient(context))
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun getOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, (5 * 1024 * 1024).toLong()))
            .addNetworkInterceptor(getResponseCacheInterceptor())
            .addInterceptor(getOfflineResponseCacheInterceptor(context))
            .addInterceptor(headersInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}