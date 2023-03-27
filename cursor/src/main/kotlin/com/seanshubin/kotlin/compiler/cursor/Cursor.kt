package com.seanshubin.kotlin.compiler.cursor

interface Cursor<T> {
    val value: T?
    val next: Cursor<T>
    fun reifyAll(): List<T> {
        val list = mutableListOf<T>()
        var currentCursor = this
        var currentValue = currentCursor.value
        while (currentValue != null) {
            list.add(currentValue)
            currentCursor = currentCursor.next
            currentValue = currentCursor.value
        }
        return list
    }
}
