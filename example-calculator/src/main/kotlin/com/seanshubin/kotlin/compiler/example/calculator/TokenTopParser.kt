package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.Parser
import com.seanshubin.kotlin.compiler.domain.Result
import com.seanshubin.kotlin.compiler.domain.TopParser

class TokenTopParser(
    private val parserMap: Map<String, Parser<Char>>
) : TopParser<Char> {
    override fun parse(name: String, cursor: Cursor<Char>): Result<Char> =
        parserMap.getValue(name).parse(cursor)
}
