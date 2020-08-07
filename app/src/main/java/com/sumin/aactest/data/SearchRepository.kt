package com.sumin.aactest.data

import android.util.Log
import com.sumin.aactest.api.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository(private val gitHubService : APIService) {
    private val TAG = SearchRepository::class.java.simpleName

    suspend fun getItems(userName: String) = gitHubService.searchCoroutine(userName).items

    fun getItemsCall(userName: String) : List<UserItems> {
        var data : List<UserItems> = emptyList()

        gitHubService.searchCall(userName).enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, userResponse: Response<UserResponse>) {
                if (userResponse.isSuccessful) {
                    Log.e(TAG, "성공 : ${userResponse.raw()}")
                    val items: List<UserItems> = userResponse.body()?.items ?: emptyList()
                    Log.e(TAG, "성공 : ${items.size}")
//                    _userItems.value = items
                    data = items
                } else {
//                    _userItems.value = emptyList()
                    data = emptyList()
                    Log.e(TAG, userResponse.message())
                }

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d(TAG, "실패 : ${t.message}")
            }
        })

        return data
    }

/*
    fun getRxItems(userName: String) : List<UserItems> {
        var data : Observable List<UserItems> = emptyList()

        gitHubService.searchRx(userName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userResponse ->
                val items : List<UserItems> = userResponse.items
                Log.e(TAG, "성공 : ${items?.size}")
                data = items
            }, { thowable ->
                Log.d(TAG, "실패 : ${thowable.message}")
            }, {
                Log.d(TAG, "Complete")
            })

        return data
    }
*/

}