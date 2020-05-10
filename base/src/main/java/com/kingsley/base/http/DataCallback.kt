package com.kingsley.base.http

import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Types`
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class DataCallback<T> : ICallback {
    private val type: Type by lazy {
        getSuperclassTypeParameter(javaClass)
    }

    companion object {
        fun getSuperclassTypeParameter(subclass: Class<*>): Type {
            val superclass = subclass.genericSuperclass
            if (superclass is Class<*>) {
                throw RuntimeException("Miss type parameter.")
            }
            val parameterizedType = superclass as ParameterizedType
            return `$Gson$Types`.canonicalize(
                parameterizedType.actualTypeArguments[0]
            )
        }
    }

    override fun onResponse(result: String) {
        try {
            val gSon = GsonBuilder()
                .registerTypeAdapterFactory(KotlinAdapterFactory())
                .create()
            val data: T = gSon.fromJson(result, type)
            onResponse(data)
        }catch (e : Exception){
            onFail(e)
        }
    }

    abstract fun onResponse(result: T)
}