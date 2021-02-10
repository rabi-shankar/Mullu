package com.rabi.mullu.utils

import android.media.MediaRecorder
import java.io.IOException

class AudioSensor {

    private var mRecorder: MediaRecorder? = null
    private var mEMA = 0.0

    fun start(){

        if(mRecorder == null){
            mRecorder = MediaRecorder()
            mRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
            mRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mRecorder!!.setOutputFile("/dev/null")

            try {
                mRecorder!!.prepare()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            mRecorder!!.start()
            mEMA = 0.0
        }
    }

    fun stop(){

        if (mRecorder != null) {
            mRecorder!!.stop()
            mRecorder!!.release()
            mRecorder = null
        }
    }

    fun getAmplitude(): Double {
        return if (mRecorder != null) mRecorder!!.maxAmplitude / 2700.0 else 0.0
    }
}