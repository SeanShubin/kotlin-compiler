package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.cursor.CharCursor.cursor
import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.cursor.IteratorCursor

object TokenCompiler {
    fun String.toTokenCursor(): Cursor<Token> {
        val charCursor = this.cursor()
        val tokenIterator = TokenIterator.fromCharCursor(charCursor)
        val tokenCursor = IteratorCursor(tokenIterator)
        return tokenCursor
    }
}
