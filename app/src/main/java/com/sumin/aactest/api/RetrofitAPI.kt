package com.sumin.aactest.api

import com.sumin.aactest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPI {
    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null

        fun getRetrofit(): Retrofit {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit
                    .Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createOKHttpClient())
                    .build()

                INSTANCE = instance
                instance
            }
        }

       private fun createOKHttpClient(): OkHttpClient {
            val interceotor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG){
                interceotor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceotor.level = HttpLoggingInterceptor.Level.NONE
            }

            return OkHttpClient
                .Builder()
                .addNetworkInterceptor(interceotor)
                .build()
        }
    }
}