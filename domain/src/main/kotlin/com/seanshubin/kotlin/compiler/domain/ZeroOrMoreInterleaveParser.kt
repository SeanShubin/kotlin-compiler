package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.ParseUtil.consumeManyInterleave

class ZeroOrMoreInterleaveParser<T>(
    override val name: String,
    private val outer: Parser<T>,
    private val inner: Parser<T>
) : Parser<T> {
    override fun parse(lookup:(String)->Parser<T>, start: Cursor<T>): ParseResult<T> {
        val (current, list, history) = consumeManyInterleave(lookup, start, outer, inner)
        return ParseResult.Success(history, listOf(name), start, current, Tree.Branch(name, list))
    }
}
