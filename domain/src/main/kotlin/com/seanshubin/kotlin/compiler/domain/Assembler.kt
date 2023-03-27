package com.seanshubin.kotlin.compiler.domain

interface Assembler<SourceType, ConstructedType> {
    val name: String
    fun assemble(tree: Tree<SourceType>): ConstructedType
}