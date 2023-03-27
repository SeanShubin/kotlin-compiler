package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class OneOfValueParser<T>(override val name: String, private val values: List<T>) : Parser<T> {
    constructor(name: String, vararg values: T) : this(name, values.toList())

    override fun parse(start: Cursor<T>): Result<T> {
        val actual = start.value ?: return Result.Failure(
            listOf(name),
            start,
            "expected one of ${values.joinToString(", ", "[", "]")}, got end"
        )
        values.forEach { expected ->
            if (actual == expected) {
                return Result.Success(listOf(name), start, start.next, Tree.Leaf(name, actual))
            }
        }
        return Result.Failure(
            listOf(name),
            start,
            "expected one of ${values.joinToString(", ", "[", "]")}, got '$actual'"
        )
    }
}
