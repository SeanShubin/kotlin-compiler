package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

data class CursorTreesHistory<T>(
    val cursor: Cursor<T>,
    val trees:List<Tree<T>>,
    val history:List<ParseResult<T>>)
