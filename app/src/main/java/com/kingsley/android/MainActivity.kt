package com.kingsley.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kingsley.base.http.DataCallback
import com.kingsley.base.http.ICallback
import com.kingsley.base.http.HttpManger
import com.kingsley.base.http.OkHttpEngine
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_text.setOnClickListener { HttpManger.with()
            .engine(OkHttpEngine)
            .baseUrl("https://beta-api.qoo-app.com/")
            .url("v10/comments")
            .addHeader("type", "apps")
            .addHeader("object_id", "10996")
            .addHeader("supported_abis", "arm64-v8a%2Carmeabi-v7a%2Carmeabi")
            .addHeader("device", "cool_c1")
            .addHeader("locale", "ko")
            .addHeader("opengl", "196609")
            .addHeader("rooted", "false")
            .addHeader("screen", "1080%2C1920")
            .addHeader("userId", "39264662")
            .addHeader("device_model", "C106-9")
            .addHeader("sdk_version", "23")
            .addHeader("user_id", "39264662")
            .addHeader("version_code", "307")
            .addHeader("version_name", "8.0.7")
            .addHeader("os", "android+6.0.1")
            .addHeader("adid", "504a3a94-8b01-40b6-9f67-b31079909033")
            .addHeader("uuid", "6e20fc7f-b9f4-4d05-8571-d81b930e072e")
            .addHeader("device_id", "6e20fc7f-b9f4-4d05-8571-d81b930e072e")
            .addHeader("package_id", "com.qooapp.qoohelper.debug")
            .addHeader("otome", "0")
            .addHeader("token", "e85402ea50161fe5393caea3f5c112f2be6f0438")
            .addHeader("android_id", "b395a34d523845bf")
            .addHeader("sa_distinct_id", "b395a34d523845bf")
            .addHeader("density", "420")
            .addHeader("patch_code", "48")
            .addHeader("system_locale", "zh_HK")
            .callback(object : DataCallback<Demo>(){
                override fun onResponse(result: Demo) {
                    Log.d("xxxx", result.toString())
                }

                override fun onFail(exception: Exception) {
                    Log.d("xxxx", exception.message)
                }
            }).execute() }

    }
}
