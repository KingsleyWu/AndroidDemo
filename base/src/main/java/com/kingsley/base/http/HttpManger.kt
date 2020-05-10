package com.kingsley.base.http

import java.lang.IllegalArgumentException

class HttpManger {
    private var method: Method
    private var baseUrl: String
    private var engine: IEngine
    private var request: Request

    init {
        method = Method.GET
        request = Request()
        engine = OkHttpEngine
        baseUrl = ""
    }

    companion object {
        fun with(): HttpManger {
            return HttpManger()
        }
    }

    fun baseUrl(baseUrl : String) : HttpManger{
        this.baseUrl = baseUrl
        return this
    }

    fun engine(engine: IEngine): HttpManger {
        this.engine = engine
        return this
    }

    fun get(): HttpManger {
        method = Method.GET
        return this
    }

    fun post(): HttpManger {
        method = Method.POST
        return this
    }

    fun delete(): HttpManger {
        method = Method.DELETED
        return this
    }

    fun patch(): HttpManger {
        method = Method.PATCH
        return this
    }

    fun put(): HttpManger {
        method = Method.PUT
        return this
    }

    fun method(method: Method): HttpManger {
        this.method = method
        return this
    }

    fun addHeader(key: String, value: String): HttpManger {
        request.addHeader(key, value)
        return this
    }

    fun addHeaders(headers: HashMap<String, String>): HttpManger {
        request.addHeaders(headers)
        return this
    }

    fun addParam(key: String, value: Any): HttpManger {
        request.addParam(key, value)
        return this
    }

    fun addParams(params: HashMap<String, Any>): HttpManger {
        request.addParams(params)
        return this
    }

    fun url(url: String): HttpManger {
        request.url = baseUrl + url
        return this
    }

    fun callback(callback: ICallback): HttpManger {
        request.callback = callback
        return this
    }

    fun execute() {
        request.callback.onPreExecute()
        if(request.url.isNotEmpty()){
            when (method) {
                Method.GET -> engine.get(request)
                Method.POST -> engine.post(request)
                Method.DELETED -> engine.deleted(request)
                Method.PATCH -> engine.patch(request)
                Method.PUT -> engine.put(request)
            }
        } else{
            request.callback.onFail(IllegalArgumentException("url can't be empty"))
        }
    }

}