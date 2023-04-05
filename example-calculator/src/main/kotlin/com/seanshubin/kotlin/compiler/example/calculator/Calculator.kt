package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Tree

object Calculator {
    fun parseTree(expression:String): Tree<Token> = ExpressionTopParser.parseSuccess("expression", expression).tree
    fun eval(expression:String):Int = ExpressionTopAssembler.assemble(expression).eval()
}
