package com.kingsley.base.http

import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

object OkHttpEngine : IEngine {
    private var client: OkHttpClient

    init {
        // 创建日志拦截器 HttpLogger() 是自定义的类
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        client = OkHttpClient.Builder()
            .connectTimeout(5L, TimeUnit.SECONDS)
            .readTimeout(5L, TimeUnit.SECONDS)
            .writeTimeout(5L, TimeUnit.SECONDS)
            // 添加日志拦截拦截器
            .addInterceptor(logInterceptor)
            .build()
    }

    fun addInterceptor(interceptor: Interceptor){
        client = client.newBuilder().addInterceptor(interceptor).build()
    }

    override fun get(request: Request) {
        val httpUrlBuilder = request.url.toHttpUrlOrNull()?.newBuilder()
        if (request.headSet.size > 1) {
            request.headSet.forEach {
                httpUrlBuilder?.addQueryParameter(it.key, it.value)
            }
        }
        val builder : okhttp3.Request.Builder
        builder = if (httpUrlBuilder != null){
            okhttp3.Request.Builder().url(httpUrlBuilder.build())
        } else{
            val params = StringBuilder()
            if (request.headSet.size > 1) {
                params.append("?")
                request.headSet.forEach {
                    params.append(it.key).append("=").append(it.value).append("&")
                }
                params.delete(params.lastIndexOf("&"), params.length)
            }
            okhttp3.Request.Builder().url("${request.url}$params")
        }
        enqueue(builder.build(), request.callback)
    }

    private fun setHeader(request: Request): okhttp3.Request.Builder {
        val builder = okhttp3.Request.Builder().url(request.url)
        if (request.headSet.size > 1) {
            request.headSet.forEach {
                builder.addHeader(it.key, it.value)
            }
        }
        return builder
    }

    private fun getRequestBody(request: Request): RequestBody {
        val json = JSONObject()
        if (request.paramSet.size > 1) {
            request.paramSet.forEach {
                json.put(it.key, it.value)
            }
        }
        return json.toString()
            .toRequestBody("application/json".toMediaType())
    }

    override fun post(request: Request) {
        val builder = setHeader(request)
            .post(getRequestBody(request))
        enqueue(builder.build(), request.callback)
    }

    override fun deleted(request: Request) {
        val builder = setHeader(request)
            .delete(getRequestBody(request))
        enqueue(builder.build(), request.callback)
    }

    override fun patch(request: Request) {
        val builder = setHeader(request)
            .patch(getRequestBody(request))
        enqueue(builder.build(), request.callback)
    }

    override fun put(request: Request) {
        val builder = setHeader(request)
            .put(getRequestBody(request))
        enqueue(builder.build(), request.callback)
    }

    private fun enqueue(request: okhttp3.Request, callback: ICallback) {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFail(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { callback.onResponse(it) }
            }
        })
    }
}
