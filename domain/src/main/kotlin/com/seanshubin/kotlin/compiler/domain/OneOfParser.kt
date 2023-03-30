package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class OneOfParser<T>(override val name: String, private val parserNames: List<String>) : Parser<T> {
    constructor(name: String, vararg parserNames: String) : this(name, parserNames.toList())

    override fun parse(lookup:(String)->Parser<T>, start: Cursor<T>): ParseResult<T> {
        val parsers = parserNames.map(lookup)
        val history = mutableListOf<ParseResult<T>>()
        parsers.forEach { parser ->
            when (val result = parser.parse(lookup, start)) {
                is ParseResult.Success -> return result
                else -> history.add(result)
            }
        }
        val parserNames = parsers.joinToString("', '", "['", "']") { it.name }
        return ParseResult.Failure(history, listOf(name), start, "none of the options matched, tried $parserNames")
    }
}
