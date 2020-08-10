package com.sumin.aactest.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sumin.aactest.R
import com.sumin.aactest.databinding.BaseWebviewBinding
import kotlinx.android.synthetic.main.base_webview.*

class BaseWebView() : AppCompatActivity() {
    private val TAG = BaseWebView::class.java.simpleName

    var url: String? = ""
    var title: String = "AAC Test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: BaseWebviewBinding =
            DataBindingUtil.setContentView(this,
                R.layout.base_webview
            )

        binding.lifecycleOwner = this

        getIntent(intent)
        actionBar?.title = title
        webViewinit()
        webView.loadUrl(url)
    }

    fun getIntent(intent: Intent?){
        intent?.let {
            url = intent.getStringExtra("url")
            title = intent.getStringExtra("title") ?: "AAC Test"
            Log.e(TAG, "url : ${url} / title : ${title}")
        }
    }

    fun webViewinit(){
        val setting = webView.settings
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
    }
}