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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    private val _userRxItems = MutableLiveData<List<UserItems>>()
    val userRxItems : LiveData<List<UserItems>> = _userRxItems

    private val _query = MutableLiveData<String>()
    val query : MutableLiveData<String> = _query

    private val  _userResult = MutableLiveData<List<User>>()
    val userResult : LiveData<List<User>> = _userResult

    private val _isLike = MutableLiveData<Boolean>()
    val isLike : LiveData<Boolean> = _isLike

    fun getItemsCoroutine(userName: String) {
        viewModelScope.launch {
            _userItems.postValue(searchRepository.getItemsCoroutine(userName))
        }
    }

    fun getItemsCall(userName: String) {
        _userItems.postValue(searchRepository.getItems(userName))
    }

    fun getRxItems(userName: String) {
        searchRepository.getRxItems(userName)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userResponse ->
                    Log.e(TAG, "Rx성공 : $userResponse")
                    val items : List<UserItems>? = userResponse.items
                    Log.e(TAG, "Rx성공 : ${items?.size}")
                    _userRxItems.postValue(items)

                }, { thowable ->
                    Log.d(TAG, "Rx실패 : ${thowable.message}")
                }, {
                    Log.d(TAG, "Rx Complete")
                })
    }

    fun addDB(user : User) = CoroutineScope(Dispatchers.IO).launch{
        local.insert(user)
    }

    fun getAllUsers(){
        Log.e(TAG, "getAllUsers")
        CoroutineScope(Dispatchers.IO).launch {
           _userResult.postValue(local.getAllUsers())
        }
    }

    fun searchUser(userName: String) {
        Log.e("API", "searchUser${userName}")
//        _userResult.postValue(findUser(userName))
    }

    fun clickLike(login : String){
        Log.e("TEST", "$login : clickLike")
//        _isLike.value = !isLike.value!!
    }

    fun findUser(user: String) {
        Log.e(TAG, "findUser : ${user}")
        CoroutineScope(Dispatchers.IO).launch {
            _userResult.postValue(local.findUser(user))
        }
    }
}