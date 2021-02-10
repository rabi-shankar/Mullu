package com.rabi.mullu.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rabi.mullu.R
import com.rabi.mullu.utils.hideStatusBar
import com.rabi.mullu.utils.load
import com.rabi.mullu.utils.loadGif
import kotlinx.android.synthetic.main.activity_lock.*

class LockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)
        hideStatusBar()

        background.loadGif(R.raw.lock_background)

        val password = "7125"
        unlock_btn.setOnClickListener {
            val key = lock_key.text.toString()
            if(key.length == 4 && key  == password){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show()
                lock_key.text.clear()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        hideStatusBar()
    }
}