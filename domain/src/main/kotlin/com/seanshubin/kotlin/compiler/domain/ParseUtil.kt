package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

object ParseUtil {
    fun <T> parseMany(cursor: Cursor<T>, parser: Parser<T>): Pair<Cursor<T>, List<Tree<T>>> {
        var currentCursor = cursor
        var lastSuccess = true
        val list = mutableListOf<Tree<T>>()
        while (lastSuccess) {
            when (val result = parser.parse(currentCursor)) {
                is Result.Success -> {
                    list.add(result.tree)
                    currentCursor = result.current
                }
                is Result.Failure -> {
                    lastSuccess = false
                }
            }
        }
        return Pair(currentCursor, list)
    }

    fun <T> consumeManyInterleave(
        cursor: Cursor<T>,
        outer: Parser<T>,
        inner: Parser<T>
    ): Pair<Cursor<T>, List<Tree<T>>> {
        val list = mutableListOf<Tree<T>>()
        var currentCursor = cursor
        val firstResult = outer.parse(cursor)
        var lastSuccess = firstResult is Result.Success
        if (firstResult is Result.Success) {
            list.add(firstResult.tree)
            currentCursor = firstResult.current
        }
        while (lastSuccess) {
            when (val betweenResult = inner.parse(currentCursor)) {
                is Result.Success -> {
                    when (val expressionResult = outer.parse(betweenResult.current)) {
                        is Result.Success -> {
                            list.add(betweenResult.tree)
                            list.add(expressionResult.tree)
                            currentCursor = expressionResult.current
                        }
                        is Result.Failure -> {
                            lastSuccess = false
                        }
                    }
                }
                is Result.Failure -> {
                    lastSuccess = false
                }
            }
        }
        return Pair(currentCursor, list)
    }
}
