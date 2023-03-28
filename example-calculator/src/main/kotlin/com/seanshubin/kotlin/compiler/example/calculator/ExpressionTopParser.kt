package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.Parser
import com.seanshubin.kotlin.compiler.domain.ParseResult
import com.seanshubin.kotlin.compiler.domain.TopParser

class ExpressionTopParser(
    private val parserMap: Map<String, Parser<Token>>
) : TopParser<Token> {
    override fun parse(name: String, cursor: Cursor<Token>): ParseResult<Token> =
        parserMap.getValue(name).parse(cursor)
}
