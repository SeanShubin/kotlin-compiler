package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.ParseResult
import com.seanshubin.kotlin.compiler.domain.TopAssembler
import com.seanshubin.kotlin.compiler.domain.Tree

object ExpressionTopAssembler : TopAssembler<Token, Expression> {
    fun assemble(s:String):Expression {
        when(val result = ExpressionTopParser.parse("expression", s)){
            is ParseResult.Success -> {
                return assemble(result.tree)
            }
            is ParseResult.Failure -> {
                val messageHeader = "Unable to evaluate '$s'"
                val messageLines = listOf(messageHeader) + result.toLines()
                val message = messageLines.joinToString("\n")
                throw RuntimeException(message)
            }
        }
    }
    override fun assemble(tree: Tree<Token>): Expression =
        ExpressionAssemblerRepository.map.getValue(tree.name).assemble(ExpressionAssemblerRepository.map::getValue, tree)
}
