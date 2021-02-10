package com.rabi.mullu.fragment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import com.rabi.mullu.BuildConfig
import com.rabi.mullu.R
import com.rabi.mullu.model.Card
import com.rabi.mullu.utils.hideStatusBar
import com.rabi.mullu.utils.load
import com.rabi.mullu.utils.loadGif
import kotlinx.android.synthetic.main.about_card_layout.*
import kotlinx.android.synthetic.main.normal_card_layout.*

class AboutFragment() : Fragment(R.layout.about_card_layout){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        app_version.text = BuildConfig.VERSION_NAME
        val code = "Developer<br><br> Rabi shankar <br><a href='http://www.aboutrabi.weebly.com'>http://www.aboutrabi.weebly.com</a>."
        developer.text = code.toSpanned()
        developer.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun String.toSpanned(): Spanned {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        }else{
            @Suppress("DEPRECATION")
            return Html.fromHtml(this)
        }

    }
}
