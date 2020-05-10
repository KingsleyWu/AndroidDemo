package com.kingsley.base.http

class Request {
    var url : String = ""
    var headSet : HashMap<String, String> = HashMap()
    var paramSet : HashMap<String, Any> = HashMap()
    var callback: ICallback = DefaultCallback()

    fun addHeader(key : String, value : String) : Request{
        headSet[key] = value
        return this
    }

    fun addHeaders(headers : HashMap<String, String>) : Request{
        headSet.putAll(headers)
        return this
    }

    fun addParam(key : String, value : Any) : Request{
        paramSet[key] = value
        return this
    }

    fun addParams(params : HashMap<String, Any>) : Request{
        paramSet.putAll(params)
        return this
    }
}
