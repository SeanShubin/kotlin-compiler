package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.Result
import com.seanshubin.kotlin.compiler.domain.TopAssembler
import com.seanshubin.kotlin.compiler.domain.TopParser

class TokenIterator(
    private val start: Cursor<Char>,
    private val parser: TopParser<Char>,
    private val assembler: TopAssembler<Char, Token>
) : Iterator<Token> {
    private var current: Cursor<Char> = start
    private var value: Token? = null

    init {
        parseNext()
    }

    override fun hasNext(): Boolean = value != null

    override fun next(): Token {
        val oldValue = value
        return if (oldValue == null) {
            throw RuntimeException("no more elements")
        } else {
            parseNext()
            oldValue
        }
    }

    private fun parseNext() {
        val result = parser.parse("token", current)
        when (result) {
            is Result.Success -> {
                current = result.current
                value = assembler.assemble(result.tree)
            }
            is Result.Failure -> {
                value = null
                ensureCursorIsAtEnd(result)
            }
        }
    }

    private fun ensureCursorIsAtEnd(result: Result.Failure<Char>) {
        if (current.value != null) {
            val remain = current.reifyAll().joinToString("")
            val message = listOf(result.message, "remain: '$remain'").joinToString("\n")
            throw RuntimeException(message)
        }
    }

    companion object {
        fun fromCharCursor(cursor: Cursor<Char>): TokenIterator {
            val parser = TokenTopParser(TokenParserRepository.map)
            val assembler = TokenTopAssembler(TokenAssemblerRepository.map)
            return TokenIterator(cursor, parser, assembler)
        }
    }
}