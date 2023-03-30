package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class SequenceParser<T>(override val name: String, private val parserNames: List<String>) : Parser<T> {
    constructor(name: String, vararg values: String) : this(name, values.toList())
    override fun parse(lookup:(String)->Parser<T>, start: Cursor<T>): ParseResult<T> {
        val parsers = parserNames.map(lookup)
        var current = start
        val list = mutableListOf<Tree<T>>()
        val history= mutableListOf<ParseResult<T>>()
        parsers.forEach { parser ->
            val result = parser.parse(lookup, current)
            history.add(result)
            when (result) {
                is ParseResult.Success -> {
                    current = result.current
                    list.add(result.tree)
                }
                is ParseResult.Failure -> {
                    return ParseResult.Failure(history,listOf(name) + result.path, start, result.reason)
                }
            }
        }
        return ParseResult.Success(history, listOf(name), start, current, Tree.Branch(name, list))
    }
}
