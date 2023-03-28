package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class TypeOfParser<T>(override val name: String, private val expected:Class<out T>) : Parser<T> {
    override fun parse(start: Cursor<T>): ParseResult<T> {
        val actualValue = start.value ?: return ParseResult.Failure(listOf(name), start, "Expected type ${expected.simpleName}, got end")
        val actual = actualValue.javaClass
        return if(actual == expected){
            ParseResult.Success(listOf(name), start, start.next, Tree.Leaf(name, actualValue))
        } else {
            ParseResult.Failure(listOf(name), start, "Expected type ${expected.simpleName}, got ${actual.simpleName}")
        }
    }
}