package com.seanshubin.kotlin.compiler.example.calculator

object Calculator {
    fun eval(expression:String):Int = ExpressionTopAssembler.assemble(expression).eval()
}
