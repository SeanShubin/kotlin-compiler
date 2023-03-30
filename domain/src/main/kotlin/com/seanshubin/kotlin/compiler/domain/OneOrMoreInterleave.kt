package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.ParseUtil.consumeManyInterleave

class OneOrMoreInterleave<T>(
    override val name: String,
    private val outerName: String,
    private val innerName: String
) : Parser<T> {
    override fun parse(lookup:(String)->Parser<T>, start: Cursor<T>): ParseResult<T> {
        val outer = lookup(outerName)
        val inner = lookup(innerName)
        val (current, list, history) = consumeManyInterleave(lookup, start, outer, inner)
        return if (list.isEmpty()) ParseResult.Failure(history, listOf(name), start, "at least one match required")
        else ParseResult.Success(history, listOf(name), start, current, Tree.Branch(name, list))
    }
}
