package com.sun.voice

object TextUtil {

    fun textAnalysis(content: String){
        //段落分割
        var paragraphDivide = paragraphDivide(content)
        paragraphDivide.forEach {
            //空格过滤
            var textFilter = textFilter(it)
            //单字分割
            textFilter.forEach {

            }
        }
    }

    fun paragraphDivide(content:String):List<String>{
        return content.split("\n")
    }

    fun textFilter(content:String):List<String>{
        val list=ArrayList<String>()
        content.filter {
            " ".equals(it)
        }.forEach {
            list.add(it.toString())
        }
        return list
    }

    fun textCheck(content: String){

    }

}