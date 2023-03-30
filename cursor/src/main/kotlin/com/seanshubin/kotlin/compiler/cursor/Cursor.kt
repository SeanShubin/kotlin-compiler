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
    fun between(other:Cursor<T>):List<T>{
        val list = mutableListOf<T>()
        var current = this
        while(current != other){
            val currentValue = current.value ?: throw RuntimeException("Reached end of this cursor before finding other cursor")
            list.add(currentValue)
            current = current.next
        }
        return list
    }
    fun filter(predicate: (T)->Boolean):Cursor<T> = FilteringCursor(this, predicate)
}
