package com.kingsley.android

data class Demo(
    val code: Int,
    val data: Data?,
    val message: String?
)

data class Data(
    val filter: Filter?,
    val guide: Guide?,
    val items: List<Any>?,
    val pager: Pager?
)

data class Filter(
    val langs: List<Lang>?,
    val selected: List<String>?,
    val sorts: List<Sort>?
)

data class Guide(
    val content: String?,
    val jump_link: String?,
    val jump_text: String?
)

data class Pager(
    val next: String?,
    val total: Int
)

data class Lang(
    val key: String?,
    val name: String?
)

data class Sort(
    val key: String?,
    val name: String?
)