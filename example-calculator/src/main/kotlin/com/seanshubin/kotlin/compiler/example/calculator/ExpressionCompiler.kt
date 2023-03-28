package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.cursor.CharCursor.cursor
import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.cursor.IteratorCursor

object ExpressionCompiler {
    fun Cursor<Token>.toExpressionCursor(): Cursor<Expression> {
        val expressionIterator = ExpressionIterator.fromTokenCursor(this)
        val expressionCursor = IteratorCursor(expressionIterator)
        return expressionCursor
    }
}
