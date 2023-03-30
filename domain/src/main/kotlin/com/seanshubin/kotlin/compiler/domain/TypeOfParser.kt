package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class TypeOfParser<T>(override val name: String, private val expected:Class<out T>) : Parser<T> {
    override fun parse(lookup:(String)->Parser<T>, start: Cursor<T>): ParseResult<T> {
        val actualValue = start.value ?: return ParseResult.Failure(emptyList(), listOf(name), start, "Expected type ${expected.simpleName}, got end")
        val actual = actualValue.javaClass
        return if(actual == expected){
            ParseResult.Success(emptyList(), listOf(name), start, start.next, Tree.Leaf(name, actualValue))
        } else {
            ParseResult.Failure(emptyList(), listOf(name), start, "Expected type ${expected.simpleName}, got ${actual.simpleName}")
        }
    }
}