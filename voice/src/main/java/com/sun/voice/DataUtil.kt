package com.sun.voice

import android.content.Context

object DataUtil {

    fun getData(context: Context): List<VoiceBean> {
        var fileString = LocalFileUtil.readLocalJsonFile(context, VoiceConfig.commonJson)
        if (fileString.isEmpty())
            return ArrayList()
        var beanList = GsonUtil.gsonToList(fileString, VoiceBean::class.java)
        return beanList;
    }
}