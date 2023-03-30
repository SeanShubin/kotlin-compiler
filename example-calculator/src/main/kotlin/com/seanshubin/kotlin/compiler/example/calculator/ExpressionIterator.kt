package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.cursor.Cursor
import com.seanshubin.kotlin.compiler.domain.ParseResult
import com.seanshubin.kotlin.compiler.domain.TopAssembler
import com.seanshubin.kotlin.compiler.domain.TopParser

class ExpressionIterator(
    private val start: Cursor<Token>,
    private val parser: TopParser<Token>,
    private val assembler: TopAssembler<Token, Expression>
) : Iterator<Expression> {
    private var current: Cursor<Token> = start
    private var value: Expression? = null

    init {
        parseNext()
    }

    override fun hasNext(): Boolean = value != null

    override fun next(): Expression {
        val oldValue = value
        return if (oldValue == null) {
            throw RuntimeException("no more elements")
        } else {
            parseNext()
            oldValue
        }
    }

    private fun parseNext() {
        val result = parser.parse("expression", current)
        when (result) {
            is ParseResult.Success -> {
                current = result.current
                value = assembler.assemble(result.tree)
            }
            is ParseResult.Failure -> {
                value = null
                ensureCursorIsAtEnd(result)
            }
        }
    }

    private fun ensureCursorIsAtEnd(result: ParseResult.Failure<Token>) {
        if (current.value != null) {
            val remain = current.reifyAll().joinToString("")
            val message = listOf(result.message, "remain: '$remain'").joinToString("\n")
            throw RuntimeException(message)
        }
    }

    companion object {
        fun fromTokenCursor(cursor: Cursor<Token>): ExpressionIterator {
            val parser = ExpressionTopParser
            val assembler = ExpressionTopAssembler
            return ExpressionIterator(cursor, parser, assembler)
        }
    }
}