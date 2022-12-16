package com.sun.voice

data class VoiceBean(var name: String, var pinyin: String) {
    override fun toString(): String {
        return name
    }
}
