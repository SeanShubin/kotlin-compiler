package com.seanshubin.kotlin.compiler.cursor

import com.seanshubin.kotlin.compiler.cursor.IteratorCursor.Companion.cursor

object CharCursor {
    fun String.cursor(): Cursor<Char> = iterator().cursor()
}