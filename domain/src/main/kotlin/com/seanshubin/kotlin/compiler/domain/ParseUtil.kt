package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

object ParseUtil {
    fun <T> parseMany(lookup:(String)->Parser<T>, cursor: Cursor<T>, parser: Parser<T>): CursorTreesHistory<T> {
        var currentCursor = cursor
        var lastSuccess = true
        val list = mutableListOf<Tree<T>>()
        val history = mutableListOf<ParseResult<T>>()
        while (lastSuccess) {
            val result = parser.parse(lookup, currentCursor)
            history.add(result)
            when (result) {
                is ParseResult.Success -> {
                    history.add(result)
                    list.add(result.tree)
                    currentCursor = result.current
                }
                is ParseResult.Failure -> {
                    history.add(result)
                    lastSuccess = false
                }
            }
        }
        return CursorTreesHistory<T>(currentCursor, list, history)
    }

    fun <T> consumeManyInterleave(
        lookup:(String)->Parser<T>,
        cursor: Cursor<T>,
        outer: Parser<T>,
        inner: Parser<T>
    ): CursorTreesHistory<T> {
        val list = mutableListOf<Tree<T>>()
        var currentCursor = cursor
        val history = mutableListOf<ParseResult<T>>()
        val firstResult = outer.parse(lookup, cursor)
        history.add(firstResult)
        var lastSuccess = firstResult is ParseResult.Success
        if (firstResult is ParseResult.Success) {
            list.add(firstResult.tree)
            currentCursor = firstResult.current
        }
        while (lastSuccess) {
            val betweenResult = inner.parse(lookup, currentCursor)
            history.add(betweenResult)
            when (betweenResult) {
                is ParseResult.Success -> {
                    val expressionResult = outer.parse(lookup, betweenResult.current)
                    history.add(expressionResult)
                    when (expressionResult) {
                        is ParseResult.Success -> {
                            list.add(betweenResult.tree)
                            list.add(expressionResult.tree)
                            currentCursor = expressionResult.current
                        }
                        is ParseResult.Failure -> {
                            lastSuccess = false
                        }
                    }
                }
                is ParseResult.Failure -> {
                    history.add(betweenResult)
                    lastSuccess = false
                }
            }
        }
        return CursorTreesHistory<T>(currentCursor, list, history)
    }
}
