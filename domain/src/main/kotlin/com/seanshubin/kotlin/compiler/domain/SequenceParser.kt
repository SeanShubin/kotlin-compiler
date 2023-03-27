package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class SequenceParser<T>(override val name: String, private val parsers: List<Parser<T>>) : Parser<T> {
    override fun parse(start: Cursor<T>): Result<T> {
        var current = start
        val list = mutableListOf<Tree<T>>()
        parsers.forEach { parser ->
            when (val result = parser.parse(current)) {
                is Result.Success -> {
                    current = result.current
                    list.add(result.tree)
                }
                is Result.Failure -> {
                    return Result.Failure(listOf(name) + result.path, start, result.reason)
                }
            }
        }
        return Result.Success(listOf(name), start, current, Tree.Branch(name, list))
    }
}
