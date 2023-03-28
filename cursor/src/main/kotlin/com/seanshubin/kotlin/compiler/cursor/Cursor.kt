package com.seanshubin.kotlin.compiler.cursor

import java.util.function.BiPredicate

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
    fun filter(predicate: (T)->Boolean):Cursor<T> = FilteringCursor(this, predicate)
}
