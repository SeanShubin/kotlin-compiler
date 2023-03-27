package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class ValueOfParser<T>(override val name: String, private val expected: T) : Parser<T> {
    override fun parse(start: Cursor<T>): Result<T> =
        when (val actual = start.value) {
            null -> Result.Failure(listOf(name), start, "expected $expected, got end")
            expected -> Result.Success(listOf(name), start, start.next, Tree.Leaf(name, actual))
            else -> Result.Failure(listOf(name), start, "expected $expected, got $actual")
        }
}
