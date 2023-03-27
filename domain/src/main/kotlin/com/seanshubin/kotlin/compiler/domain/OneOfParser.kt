package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class OneOfParser<T>(override val name: String, private val parsers: List<Parser<T>>) : Parser<T> {
    constructor(name: String, vararg parsers: Parser<T>) : this(name, parsers.toList())

    override fun parse(start: Cursor<T>): Result<T> {
        parsers.forEach { parser ->
            when (val result = parser.parse(start)) {
                is Result.Success -> return result
                else -> {}
            }
        }
        val parserNames = parsers.joinToString("', '", "['", "']") { it.name }
        return Result.Failure(listOf(name), start, "none of the options matched, tried $parserNames")
    }
}
