package com.sumin.aactest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sumin.aactest.databinding.ActivityMainBinding
import com.sumin.aactest.utilities.InjectorUtils
import com.sumin.aactest.viewmodel.APIViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG: String = MainActivity::class.java.simpleName

    val model : APIViewModel by viewModels{
        InjectorUtils.getAPIViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = model
        binding.lifecycleOwner = this

        initNavigation()
        initEditText()
    }

    /**
     * BottomNavigationView 초기화
     * */
    fun initNavigation(){
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_api, R.id.navigation_rx, R.id.navigation_local))
        setupActionBarWithNavController(navController, appBarConfiguration)
        mBottomNavi.setupWithNavController(navController)
    }

    /**
     * EditText 초기화
     * */
    fun initEditText(){
        mSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                when(mBottomNavi.selectedItemId) {
                    R.id.navigation_api -> {
                        getUserAPI(s.toString())
                    }

                    R.id.navigation_rx -> {
                        getUserRxAPI(s.toString())
                    }

                    R.id.navigation_local -> {
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    /**
     * Github 유져 검색
     * @param user 검색하고자 하는 유져 ID
     * */
    fun getUserAPI(user : String) {
        Log.e("API", "getUserAPI${user}")
//        model.getData(user)
        model.getItems(user)
    }

    /**
     * Github 유져 검색 Rx
     * @param user 검색하고자 하는 유져 ID
     * */
    fun getUserRxAPI(user : String) {
        Log.e("API", "getUserAPI${user}")
        model.getRxItems(user)
    }

}