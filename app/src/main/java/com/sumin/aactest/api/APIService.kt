package com.sumin.aactest.api

import com.sumin.aactest.BuildConfig
import com.sumin.aactest.data.UserResponse
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    // API Info - "https://developer.github.com/v3/search/#search-users"
    /**
     * Github User 검색(sort와 order는 기본값 사용)
     * @param user 검색하고자 하는 유져 ID
     * */
    @GET("search/users")
    suspend fun searchCoroutine(@Query("q") user: String?): UserResponse


    /**
     * Github User 검색(sort와 order는 기본값 사용)
     * @param user 검색하고자 하는 유져 ID
     * */
    @GET("search/users")
    fun searchCall(@Query("q") user: String?): Call<UserResponse>

    /**
     * Retrofit에서 RxJava 사용시 Call -> Flowable로 변환
     * Github User 검색(sort와 order는 기본값 사용)
     * @param user 검색하고자 하는 유져 ID
    */
    @GET("search/users")
    fun searchRx(@Query("q") user: String?): Flowable<UserResponse>

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): APIService {
            val interceotor = HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG){
                    level = HttpLoggingInterceptor.Level.BODY
                } else {
                    level = HttpLoggingInterceptor.Level.NONE
                }
            }

            val  client = OkHttpClient
                .Builder()
                .addInterceptor(interceotor)
                .build()

            return Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build().create(APIService::class.java)
        }
    }
}