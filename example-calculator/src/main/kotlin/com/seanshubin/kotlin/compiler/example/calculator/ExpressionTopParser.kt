package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.Parser
import com.seanshubin.kotlin.compiler.domain.ParseResult
import com.seanshubin.kotlin.compiler.domain.TopParser
import com.seanshubin.kotlin.compiler.example.calculator.TokenCompiler.toTokenCursor

object ExpressionTopParser : TopParser<Token> {
    fun parseSuccess(name:String, input:String):ParseResult.Success<Token> =
        parse(name, input) as ParseResult.Success<Token>
    fun parse(name:String, input:String):ParseResult<Token> =
        parse(name, input.toTokenCursor().filter{it !is Token.WhitespaceBlock})
    override fun parse(name: String, cursor: Cursor<Token>): ParseResult<Token> =
        ExpressionParserRepository.map.getValue(name).parse(ExpressionParserRepository.map::getValue, cursor)
}
