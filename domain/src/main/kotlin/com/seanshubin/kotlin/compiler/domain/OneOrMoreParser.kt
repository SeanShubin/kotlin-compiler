package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.ParseUtil.parseMany

class OneOrMoreParser<T>(override val name: String, private val parser: Parser<T>) : Parser<T> {
    override fun parse(start: Cursor<T>): ParseResult<T> {
        val (current, list) = parseMany(start, parser)
        return if (list.isEmpty()) ParseResult.Failure(listOf(name, parser.name), start, "required at least one")
        else ParseResult.Success(listOf(name, parser.name), start, current, Tree.Branch(name, list))
    }
}
