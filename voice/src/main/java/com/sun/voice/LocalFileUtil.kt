package com.sun.voice

import android.content.Context

object LocalFileUtil {

    fun readLocalJsonFile(context: Context,fileName:String):String{
        var result = ""
        try {
            var inputStream = context.resources.assets.open(fileName)
            var byteArray = ByteArray(inputStream.available())
            inputStream.read(byteArray)
            result = String(byteArray, Charsets.UTF_8)
        }catch (e:Exception){
            VoiceLog.log(e.toString())
        }
        return result
    }

    fun readLocalRawFile(context: Context,name:String):Int{
        return context.resources.getIdentifier(name, "raw", context.packageName)
    }
}