package com.kingsley.android

import android.app.Application
import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // 输出线程信息. 默认输出
            .methodCount(0)         // 方法栈打印的个数，默认是2
            .methodOffset(7)        // 设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
            .logStrategy(LogCatStrategy())
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}

class LogCatStrategy: LogStrategy {
    override fun log(priority: Int, tag: String?, message: String) {
        Log.println(priority, randomKey() + tag!!, message)
    }

    private var last: Int = 0

    private fun randomKey(): String {
        var random = (10 * Math.random()).toInt()
        if (random == last) {
            random = (random + 1) % 10
        }
        last = random
        return random.toString()
    }
}