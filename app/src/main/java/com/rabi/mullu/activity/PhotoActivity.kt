package com.rabi.mullu.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.rabi.mullu.R
import com.rabi.mullu.utils.gone
import com.rabi.mullu.utils.hideStatusBar
import com.rabi.mullu.utils.visible
import kotlinx.android.synthetic.main.webciew_card_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class PhotoActivity : AppCompatActivity() {

    val scope = CoroutineScope(Job())

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webciew_card_layout)

        web_view.settings.javaScriptEnabled = true

        web_view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                progress_bar.visible()
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progress_bar.gone()
                super.onPageFinished(view, url)
            }
        }
        val url = "https://photos.google.com/share/AF1QipOHjYI29XqdYQyS_ZKGUSGTbSKrnFk5016G9IQmDzuz5xKJoycLAwc5hXeRLV62Xw?key=VUc2UDJmV29rVDB5Q2M4eV9ETEY1MzJpa0xtbkVR"
        web_view.loadUrl(url)

    }

    override fun onStart() {
        super.onStart()
        hideStatusBar()
    }

        override fun onBackPressed() {
            if(web_view != null){
                if(web_view.canGoBack()) {
                    web_view.goBack()
                }else super.onBackPressed()
            }else super.onBackPressed()
        }
}
