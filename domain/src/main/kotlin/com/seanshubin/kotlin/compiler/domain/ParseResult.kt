package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

sealed interface ParseResult<T> {
    data class Success<T>(
        val path: List<String>,
        val start: Cursor<T>,
        val current: Cursor<T>,
        val tree: Tree<T>
    ) : ParseResult<T>

    data class Failure<T>(
        val path: List<String>,
        val start: Cursor<T>,
        val reason: String
    ) : ParseResult<T> {
        val message: String get() = path.joinToString("-") + ": " + reason
    }
}
