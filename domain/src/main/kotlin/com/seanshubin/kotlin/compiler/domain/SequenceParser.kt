package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class SequenceParser<T>(override val name: String, private val parsers: List<Parser<T>>) : Parser<T> {
    constructor(name: String, vararg values: Parser<T>) : this(name, values.toList())
    override fun parse(start: Cursor<T>): ParseResult<T> {
        var current = start
        val list = mutableListOf<Tree<T>>()
        parsers.forEach { parser ->
            when (val result = parser.parse(current)) {
                is ParseResult.Success -> {
                    current = result.current
                    list.add(result.tree)
                }
                is ParseResult.Failure -> {
                    return ParseResult.Failure(listOf(name) + result.path, start, result.reason)
                }
            }
        }
        return ParseResult.Success(listOf(name), start, current, Tree.Branch(name, list))
    }
}
