package com.sun.voice

import android.util.Log

object VoiceLog {

    fun log(msg: String) {
        log(msg, Log.DEBUG)
    }

    fun log(msg: String, level: Int) {
        if (VoiceConfig.state) {
            when (level) {
                Log.VERBOSE -> Log.v(VoiceConfig.TAG, msg)
                Log.DEBUG -> Log.d(VoiceConfig.TAG, msg)
                Log.INFO -> Log.i(VoiceConfig.TAG, msg)
                Log.WARN -> Log.w(VoiceConfig.TAG, msg)
                Log.ERROR -> Log.e(VoiceConfig.TAG, msg)
                else -> Log.d(VoiceConfig.TAG, msg)
            }
        }
    }
}