package com.sun.voice

import com.google.gson.Gson
import com.google.gson.JsonParser

object GsonUtil {

    fun <T> gsonToBean(json:String,cls:Class<T>): T{
        return Gson().fromJson(json,cls)
    }

    fun <T> gsonToList(json: String,cls:Class<T>):List<T>{
        val list = ArrayList<T>()
        val gson = Gson()
        var array = JsonParser.parseString(json).asJsonArray
        array.forEach {
            list.add(gson.fromJson(it,cls))
        }
        return list
    }
}