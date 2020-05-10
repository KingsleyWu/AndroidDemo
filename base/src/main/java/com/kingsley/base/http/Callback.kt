package com.kingsley.base.http

import java.lang.Exception

/**
 * 回调
 */
interface ICallback {
    /**
     * 成功
     */
    fun onResponse(result: String)

    /**
     * 失败
     */
    fun onFail(exception: Exception)

    /**
     * 用于请求前 如加载进度条，添加参数等
     */
    fun onPreExecute(){}
}

class DefaultCallback : ICallback {
    override fun onResponse(result: String) {

    }

    override fun onFail(exception: Exception) {

    }
}