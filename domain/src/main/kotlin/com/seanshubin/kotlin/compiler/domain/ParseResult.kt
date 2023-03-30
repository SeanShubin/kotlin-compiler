package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

sealed interface ParseResult<T> {
    val history:List<ParseResult<T>>
    val message:String
    fun messageWithHistory():List<String>{
        return listOf(message) + history.flatMap { child ->
            child.messageWithHistory().map { "  $it"}
        }
    }
    fun toLines():List<String>
    data class Success<T>(
        override val history: List<ParseResult<T>>,
        val path: List<String>,
        val start: Cursor<T>,
        val current: Cursor<T>,
        val tree: Tree<T>
    ) : ParseResult<T>{
        override val message:String get() {
            val listString = start.between(current).joinToString(", ", "[", "]")
            val pathString = path.joinToString(",")
            return "success $pathString: $listString"
        }

        override fun toLines(): List<String> = tree.toLines()
    }

    data class Failure<T>(
        override val history: List<ParseResult<T>>,
        val path: List<String>,
        val start: Cursor<T>,
        val reason: String
    ) : ParseResult<T> {
        override val message: String get()  {
            val currentString = start.value?.toString() ?: "<end>"
            val pathString = path.joinToString(",")
            return "failure $pathString: $currentString"
        }

        override fun toLines(): List<String> = messageWithHistory()
    }
}
