package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

interface TopParser<T> {
    fun parse(name: String, cursor: Cursor<T>): Result<T>
}
