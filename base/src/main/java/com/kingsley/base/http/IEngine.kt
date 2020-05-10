package com.kingsley.base.http

interface IEngine {
    fun get(request : Request)
    fun post(request : Request)
    fun deleted(request : Request)
    fun patch(request : Request)
    fun put(request : Request)
}