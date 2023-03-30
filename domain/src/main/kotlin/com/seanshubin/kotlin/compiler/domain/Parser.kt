package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

interface Parser<T> {
    val name: String
    fun parse(lookup:(String) -> Parser<T>, start: Cursor<T>): ParseResult<T>
}
