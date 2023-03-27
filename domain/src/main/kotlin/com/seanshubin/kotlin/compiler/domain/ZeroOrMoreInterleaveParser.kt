package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.ParseUtil.consumeManyInterleave

class ZeroOrMoreInterleaveParser<T>(
    override val name: String,
    private val outer: Parser<T>,
    private val inner: Parser<T>
) : Parser<T> {
    override fun parse(start: Cursor<T>): Result<T> {
        val (current, list) = consumeManyInterleave(start, outer, inner)
        return Result.Success(listOf(name), start, current, Tree.Branch(name, list))
    }
}
