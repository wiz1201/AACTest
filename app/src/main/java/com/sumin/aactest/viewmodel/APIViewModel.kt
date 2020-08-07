package com.sumin.aactest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumin.aactest.data.LocalReposirtory
import com.sumin.aactest.data.SearchRepository
import com.sumin.aactest.data.User
import com.sumin.aactest.data.UserItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class APIViewModel(
    private val searchRepository : SearchRepository,
    private val local : LocalReposirtory
) : ViewModel() {
    val TAG: String = APIViewModel::class.java.simpleName

    private val _userItems = MutableLiveData<List<UserItems>>()
    val userItems : LiveData<List<UserItems>> = _userItems

    private val _query = MutableLiveData<String>()
    val query : MutableLiveData<String> = _query

    val userResult : LiveData<List<User>> =  local.getAllUsers()

    private val _isLike = MutableLiveData<Boolean>()
    val isLike : LiveData<Boolean> = _isLike

    fun getItems(userName: String) = viewModelScope.launch(Dispatchers.IO) {
        _userItems.postValue(searchRepository.getItems(userName))
    }

    fun getRxItems(userName: String) {
/*
        searchRepository.getItems(userName)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userResponse ->
                    Log.e(TAG, "성공 : $userResponse")
                    val items : List<UserItems>? = userResponse.items
                    Log.e(TAG, "성공 : ${items?.size}")
                    _userItems.postValue(items)

                }, { thowable ->
                    Log.d(TAG, "실패 : ${thowable.message}")
                }, {
                    Log.d(TAG, "Complete")
                })
*/
    }

    fun addDB(user : User) = CoroutineScope(Dispatchers.IO).launch{
        local.insert(user)
    }

    fun searchUser(userName: String) {
        Log.e("API", "searchUser${userName}")
//        _userResult.postValue(findUser(userName))
    }

    fun clickLike(login : String){
        Log.e("TEST", "$login : clickLike")
//        _isLike.value = !isLike.value!!
    }
}