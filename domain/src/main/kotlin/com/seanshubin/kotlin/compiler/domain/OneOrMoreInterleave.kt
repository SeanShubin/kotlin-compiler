package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.ParseUtil.consumeManyInterleave

class OneOrMoreInterleave<T>(
    override val name: String,
    private val outer: Parser<T>,
    private val inner: Parser<T>
) : Parser<T> {
    override fun parse(start: Cursor<T>): ParseResult<T> {
        val (current, list) = consumeManyInterleave(start, outer, inner)
        return if (list.isEmpty()) ParseResult.Failure(listOf(name), start, "at least one match required")
        else ParseResult.Success(listOf(name), start, current, Tree.Branch(name, list))
    }
}
