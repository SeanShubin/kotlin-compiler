package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class OneOfValueParser<T>(override val name: String, private val values: List<T>) : Parser<T> {
    constructor(name: String, vararg values: T) : this(name, values.toList())

    override fun parse(lookup:(String)->Parser<T>, start: Cursor<T>): ParseResult<T> {
        val actual = start.value ?: return ParseResult.Failure(
            emptyList(),
            listOf(name),
            start,
            "expected one of ${values.joinToString(", ", "[", "]")}, got end"
        )
        val history = mutableListOf<ParseResult<T>>()
        values.forEach { expected ->
            if (actual == expected) {
                return ParseResult.Success(emptyList(),  listOf(name), start, start.next, Tree.Leaf(name, actual))
            } else {
                history.add(ParseResult.Failure(
                    emptyList(),
                    listOf(name),
                    start,
                    "expected $expected, got $actual"
                ))
            }
        }
        return ParseResult.Failure(
            history,
            listOf(name),
            start,
            "expected one of ${values.joinToString(", ", "[", "]")}, got '$actual'"
        )
    }
}
