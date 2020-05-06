package com.kingsley.android.part1

import kotlin.math.max

fun main() {
    // val value 的简写 用来声明不可变的变量
    val a = 10
    // var variable 的简写 用来声明可变的变量
    var b = 10
    b *= 20
    println("a = $a  b = $b ${largerNumber(1,2)}")
}

fun largerNumber(num1 : Int, num2 : Int) =  max(num1,num2)
